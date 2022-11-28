package com.hulk.common.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.bean.BaseItemBean

/**
 * created by tzh on 2022/11/27
 */
class ProviderMultiAdapter(
    itemBeans: MutableList<BaseItemBean>,
    itemProvider: MutableList<BaseItemProvider<BaseItemBean>>
) : BaseProviderMultiAdapter<BaseItemBean>(itemBeans) {

    init {
        itemProvider.forEach {
            addItemProvider(it)
        }
    }

    override fun getItemType(data: List<BaseItemBean>, position: Int): Int {
        return data[position].itemType
    }

}