package com.hulk.common.bean

/**
 * @description 这个类定义了item包含的数据。包含布局相关的数据和需要展示的数据
 * @author: zehao.tian
 * @date: 2022/11/24
 * @param itemType item布局类型
 * @param title 标题
 */
data class BaseItemBean(
    var itemType: Int,
    var title: String? = null,
    var titles: MutableList<String>? = null,
    var isFirstPosition: Boolean = false,
    var isLastPosition: Boolean = false
)