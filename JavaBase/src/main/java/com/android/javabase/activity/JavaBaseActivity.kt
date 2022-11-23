package com.android.javabase.activity

import com.hulk.common.base.CommonListActivity

class JavaBaseActivity : CommonListActivity() {

    override fun getListData(): ArrayList<String> = arrayListOf(
        "Java 容器", "Java 泛型", "Java 注解", "Java 反射", "Java I/O", "Java 序列化",
    )

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        JavaCollectionActivity::class.java,JavaGenericActivity::class.java
    )

}