package com.hulk.common.bean

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.constants.ItemTypeConstants
import com.hulk.common.utils.UiUtils

/**
 * 页面数据构建类
 * created by tzh on 2022/11/28
 */
abstract class BaseDataProvider {
    val dataList = mutableListOf<BaseItemBean>()
    /**
     * 构建页面所有数据
     */
    abstract fun getPageData(): MutableList<BaseItemBean>

    abstract fun getItemProviderData():MutableList<BaseItemProvider<BaseItemBean>>

    /**
     * 构建一级标题item.
     */
    protected fun buildTitleItemBean(titleId: Int) =
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_TITLE, title = UiUtils.getString(titleId))

    /**
     * 构建描述内容
     */
    protected fun buildDescItemBean(descId: Int) =
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_DESC, title = UiUtils.getString(descId))

    /**
     * 构建表格行
     */
    protected fun buildTabItemBean(effect: String) =
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_TAB_ROW, titles = mutableListOf(effect))

    /**
     * 构建表格行
     */
    protected fun buildTabItemBean(structName: String, effect: String) =
        BaseItemBean(
            ItemTypeConstants.ITEM_TYPE_TAB_ROW,
            titles = mutableListOf(structName, effect)
        )

    /**
     * 构建表格第一行
     */
    protected fun buildTabFirstItemBean(effect: String) =
        BaseItemBean(
            ItemTypeConstants.ITEM_TYPE_TAB_ROW,
            titles = mutableListOf(effect),
            isFirstPosition = true
        )

    /**
     * 构建表格第一行
     */
    protected fun buildTabFirstItemBean(structName: String, effect: String) =
        BaseItemBean(
            ItemTypeConstants.ITEM_TYPE_TAB_ROW,
            titles = mutableListOf(structName, effect),
            isFirstPosition = true
        )

    /**
     * 构建表格最后一行
     */
    protected fun buildTabLastItemBean(effect: String) =
        BaseItemBean(
            ItemTypeConstants.ITEM_TYPE_TAB_ROW,
            titles = mutableListOf(effect),
            isLastPosition = true
        )

    /**
     * 构建表格最后一行
     */
    protected fun buildTabLastItemBean(structName: String, effect: String) =
        BaseItemBean(
            ItemTypeConstants.ITEM_TYPE_TAB_ROW,
            titles = mutableListOf(structName, effect),
            isLastPosition = true
        )
}