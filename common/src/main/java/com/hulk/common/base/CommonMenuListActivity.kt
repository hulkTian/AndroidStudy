package com.hulk.common.base

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.hulk.common.adapter.BaseListAdapter
import com.hulk.common.adapter.CommonStringListAdapter

/**
 * @description 这个类是通用的列表Activity,只支持显示String类型的数据。item的点击事件默认进行子Activity的跳转。
 */
abstract class CommonMenuListActivity : BaseListActivity() {
    private lateinit var activityArrayList: ArrayList<Class<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityArrayList = getActivityListData()
    }

    override fun initAdapter() {
        mBinding.rvList.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        mBinding.rvList.adapter = CommonStringListAdapter(this, getListData(), object :
            BaseListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                this@CommonMenuListActivity.onItemClick(position)
            }
        })
    }

    protected fun onItemClick(position: Int) {
        if (position < activityArrayList.size && !activityArrayList.isNullOrEmpty()) {
            startActivity(activityArrayList[position])
        }
    }

    abstract fun getActivityListData(): ArrayList<Class<*>>
}