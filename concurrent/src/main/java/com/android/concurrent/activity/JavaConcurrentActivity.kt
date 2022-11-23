package com.android.concurrent.activity

import com.hulk.common.base.CommonListActivity

/**
 * @description 并发编程
 * @author
 * @time
 */
class JavaConcurrentActivity: CommonListActivity() {

    override fun getListData(): ArrayList<String> = arrayListOf(
        "Java 容器", "Java 泛型", "Java 注解", "Java 反射", "Java I/O", "Java 序列化",
    )

    override fun getActivityListData(): ArrayList<Class<*>> {
        TODO("Not yet implemented")
    }

}