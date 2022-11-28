package com.android.javabase.activity

import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

class JavaBaseActivity : CommonMenuListActivity() {

    override fun getListData(): MutableList<BaseItemBean> = arrayListOf(
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java 容器"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java 泛型"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java 注解"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java 反射"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java I/O"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java 序列化"),

    )

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        JavaCollectionActivity::class.java, JavaGenericActivity::class.java
    )

}