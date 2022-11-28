package com.hulk.common.bean

import com.chad.library.adapter.base.provider.BaseItemProvider

/**
 * 页面数据构建类
 * created by tzh on 2022/11/28
 */
abstract class BaseDataProvider {

    /**
     * 构建页面所有数据
     */
    abstract fun getPageData(): MutableList<BaseItemBean>

    abstract fun getItemProviderData():MutableList<BaseItemProvider<BaseItemBean>>
}