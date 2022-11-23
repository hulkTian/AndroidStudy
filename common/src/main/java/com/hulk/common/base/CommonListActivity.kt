package com.hulk.common.base

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.hulk.common.R
import com.hulk.common.databinding.CommonLayoutRecycleViewBinding

abstract class CommonListActivity : BaseActivity<CommonLayoutRecycleViewBinding>() {
    private lateinit var activityArrayList: ArrayList<Class<*>>

    override fun getLayoutId(): Int = R.layout.common_layout_recycle_view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityArrayList = getActivityListData()
        mBinding.rvList.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        mBinding.rvList.adapter =
            BaseListAdapter(this, getListData(), object :
                BaseListAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    this@CommonListActivity.onItemClick(position)
                }
            })
    }

    protected fun onItemClick(position: Int) {
        if (position < activityArrayList.size && !activityArrayList.isNullOrEmpty()) {
            startActivity(activityArrayList[position])
        }
    }

    abstract fun getListData(): ArrayList<String>

    abstract fun getActivityListData(): ArrayList<Class<*>>
}