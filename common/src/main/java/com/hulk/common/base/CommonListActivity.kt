package com.hulk.common.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.hulk.common.R
import com.hulk.common.databinding.CommonLayoutRecycleViewBinding

abstract class CommonListActivity : AppCompatActivity() {
    private lateinit var mBinding: CommonLayoutRecycleViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.common_layout_recycle_view)
        mBinding.rvList.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        mBinding.rvList.adapter = BaseListAdapter(this, getListData(), object :
            BaseListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                this@CommonListActivity.onItemClick(position)
            }
        })
    }

    protected fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    abstract fun getListData(): ArrayList<String>

    abstract fun onItemClick(position: Int);
}