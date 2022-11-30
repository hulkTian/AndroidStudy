package com.hulk.common.adapter.provider

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hulk.common.R
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

/**
 * 表格item的provider
 * created by tzh on 2022/11/28
 */
class TabItemProvider(
    override val itemViewType: Int = ItemTypeConstants.ITEM_TYPE_TAB_ROW,
    override val layoutId: Int = R.layout.item_provider_tab
) : BaseItemProvider<BaseItemBean>() {

    override fun convert(helper: BaseViewHolder, item: BaseItemBean) {
        helper.setVisible(R.id.v_line_bottom, item.isLastPosition)
        val tvColumnOne = helper.getView<TextView>(R.id.tv_column_one)
        val tvColumnTwo = helper.getView<TextView>(R.id.tv_column_two)
        if (item.isFirstPosition) {
            tvColumnOne.gravity = Gravity.CENTER
            tvColumnTwo.gravity = Gravity.CENTER
        } else {
            tvColumnOne.gravity = Gravity.CENTER
            tvColumnTwo.gravity = Gravity.CENTER_VERTICAL
        }
        item.titles?.let {
            when (it.size) {
                1 -> {
                    tvColumnTwo.text = it[0]
                    tvColumnOne.visibility = View.GONE
                    helper.setGone(R.id.v_line_spilt, true)
                }
                2 -> {
                    tvColumnOne.text = it[0]
                    tvColumnTwo.text = it[1]
                    tvColumnOne.visibility = View.VISIBLE
                    helper.setGone(R.id.v_line_spilt, false)
                }
                else -> {

                }
            }
        }
    }
}