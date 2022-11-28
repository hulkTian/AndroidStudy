package com.hulk.common.base

import android.os.Bundle
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.R
import com.hulk.common.adapter.ProviderMultiAdapter
import com.hulk.common.bean.BaseDataProvider
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.databinding.CommonLayoutRecycleViewBinding

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/25
 */
abstract class BaseListActivity : BaseActivity<CommonLayoutRecycleViewBinding>() {
    protected lateinit var mAdapter: ProviderMultiAdapter
    protected lateinit var mDataProvider: BaseDataProvider

    override fun getLayoutId(): Int = R.layout.common_layout_recycle_view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
    }

    open fun initAdapter() {
        mAdapter = ProviderMultiAdapter(getListData(), getItemProviderData())
        mBinding.rvList.adapter = mAdapter
    }

    open fun initDataProvider() {

    }

    abstract fun getListData(): MutableList<BaseItemBean>

    abstract fun getItemProviderData():MutableList<BaseItemProvider<BaseItemBean>>
}