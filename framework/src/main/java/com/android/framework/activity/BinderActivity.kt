package com.android.framework.activity

import com.android.framework.data.BinderDataProvider
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.base.BaseListActivity
import com.hulk.common.bean.BaseItemBean

/**
 * @description Binder相关知识
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class BinderActivity : BaseListActivity() {

    override fun initDataProvider() {
        mDataProvider = BinderDataProvider()
    }

    override fun getListData(): MutableList<BaseItemBean> = mDataProvider.getPageData()

    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> =
        mDataProvider.getItemProviderData()

}