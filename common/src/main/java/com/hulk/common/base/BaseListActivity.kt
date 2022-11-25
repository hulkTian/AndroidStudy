package com.hulk.common.base

import android.os.Bundle
import com.hulk.common.R
import com.hulk.common.adapter.BaseListAdapter
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.databinding.CommonLayoutRecycleViewBinding

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/25
 */
abstract class BaseListActivity : BaseActivity<CommonLayoutRecycleViewBinding>() {

    override fun getLayoutId(): Int = R.layout.common_layout_recycle_view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
    }

    abstract fun initAdapter()

    abstract fun getListData(): ArrayList<BaseItemBean>
}