package com.android.framework.activity

import com.android.framework.data.ServiceStartProcessProvider
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.base.BaseListActivity
import com.hulk.common.bean.BaseItemBean

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class ServiceStartProcessActivity : BaseListActivity() {

    override fun initDataProvider() {
        mDataProvider = ServiceStartProcessProvider()
    }

    override fun getListData(): MutableList<BaseItemBean> = mDataProvider.getPageData()

    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> =
        mDataProvider.getItemProviderData()
}