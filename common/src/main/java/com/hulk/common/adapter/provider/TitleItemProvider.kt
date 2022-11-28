package com.hulk.common.adapter.provider

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hulk.common.R
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

/**
 * created by tzh on 2022/11/27
 */
class TitleItemProvider(
    override val itemViewType: Int = ItemTypeConstants.ITEM_TYPE_TITLE,
    override val layoutId: Int = R.layout.item_provider_title
) : BaseItemProvider<BaseItemBean>() {
    override fun convert(helper: BaseViewHolder, item: BaseItemBean) {
    }

}