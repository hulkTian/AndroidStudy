package com.android.framework.data

import com.android.framework.R
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.adapter.provider.DescItemProvider
import com.hulk.common.adapter.provider.TitleItemProvider
import com.hulk.common.bean.BaseDataProvider
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants
import com.hulk.common.utils.UiUtils

/**
 * Binder知识界面数据
 * created by tzh on 2022/11/28
 */
class BinderDataProvider : BaseDataProvider() {

    override fun getPageData(): MutableList<BaseItemBean> {
        var dataList = mutableListOf<BaseItemBean>()
        dataList.add(
            BaseItemBean(
                ItemTypeConstants.ITEM_TYPE_TITLE,
                UiUtils.getString(R.string.item_overview)
            )
        )
        return dataList
    }

    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> = mutableListOf(
        TitleItemProvider(), DescItemProvider()
    )

}