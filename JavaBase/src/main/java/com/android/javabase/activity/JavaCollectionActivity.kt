package com.android.javabase.activity

import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

class JavaCollectionActivity : CommonMenuListActivity() {

    override fun getListData(): MutableList<BaseItemBean> {
        return arrayListOf(
            /*"ArrayList",
            "Java LinkedList",
            "Java 注解",
            "Java 反射",
            "Java I/O",
            "Java 序列化",*/
            BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "ArrayList"),
            BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "LinkedList"),
        )
    }

    override fun getActivityListData(): ArrayList<Class<*>> {
        TODO("Not yet implemented")
    }

}