package com.hulk.common.adapter

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.databinding.AdapterMainBinding

/**
 * @description 通用的菜单列表
 * @author: zehao.tian
 * @date: 2022/11/24
 */
class CommonStringListAdapter(
    context: Context,
    listData: ArrayList<BaseItemBean>,
    listener: OnItemClickListener
) : BaseListAdapter(context, listData, listener) {

    override fun onItemConvert(root: ViewDataBinding, position: Int) {
        DataBindingUtil.findBinding<>(root)
        root.tvMain.text = listData[position].data.title
    }
}