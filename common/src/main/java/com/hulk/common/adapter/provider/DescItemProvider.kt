package com.hulk.common.adapter.provider

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hulk.common.R
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

/**
 * created by tzh on 2022/11/28
 */
class DescItemProvider(
    override val itemViewType: Int = ItemTypeConstants.ITEM_TYPE_DESC,
    override val layoutId: Int = R.layout.item_provider_desc
) : BaseItemProvider<BaseItemBean>() {

    override fun convert(helper: BaseViewHolder, item: BaseItemBean) {
        val tvDesc = helper.getView<TextView>(R.id.tv_desc)
        if (item.title.isNullOrEmpty()) {
            tvDesc.visibility = View.GONE
        } else {
            tvDesc.visibility = View.VISIBLE
            tvDesc.text = item.title
        }
        val ivDesc = helper.getView<ImageView>(R.id.iv_desc)
        if (item.imgRes == null) {
            ivDesc.visibility = View.GONE
        } else {
            ivDesc.visibility = View.VISIBLE
            ivDesc.setImageResource(item.imgRes!!)
        }
    }

}