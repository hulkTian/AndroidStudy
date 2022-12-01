package com.android.framework.data

import com.android.framework.R
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.adapter.provider.DescItemProvider
import com.hulk.common.adapter.provider.TabItemProvider
import com.hulk.common.adapter.provider.TitleItemProvider
import com.hulk.common.bean.BaseDataProvider
import com.hulk.common.bean.BaseItemBean

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/12/1
 */
class ServiceStartProcessProvider : BaseDataProvider() {

    override fun getPageData(): MutableList<BaseItemBean> {
        dataList.add(buildTitleItemBean(R.string.service_start_process))
        dataList.add(buildDescItemBean(R.string.service_start_description))
        return dataList
    }

    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> = mutableListOf(
        TitleItemProvider(),
        DescItemProvider(),
        TabItemProvider(),
    )
}