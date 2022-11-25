package com.hulk.common.bean

/**
 * @description 这个类定义了item包含的数据。包含布局相关的数据和需要展示的数据
 * @author: zehao.tian
 * @date: 2022/11/24
 * @param itemLayoutId item布局ID
 * @param data item展示数据
 */
data class BaseItemBean(
    var itemLayoutId: Int,
    var data: BaseDataBean
)