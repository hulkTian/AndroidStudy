package com.hulk.common.base

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.adapter.provider.MenuItemProvider
import com.hulk.common.bean.BaseItemBean

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
        super.initAdapter()
        mAdapter.setOnItemClickListener { _, _, position ->
            onItemClick(position)
        }
    }


    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> = mutableListOf(
        MenuItemProvider()
    )

    private fun onItemClick(position: Int) {
        if (position < activityArrayList.size && !activityArrayList.isNullOrEmpty()) {
            startActivity(activityArrayList[position])
        }
    }

    abstract fun getActivityListData(): ArrayList<Class<*>>
}