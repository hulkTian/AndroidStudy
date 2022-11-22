package com.android.javabase.activity

import com.hulk.common.base.CommonListActivity

open class JavaBaseActivity : CommonListActivity() {

    override fun getListData(): ArrayList<String> {
        return arrayListOf(
            "Java 容器",
            "Java 泛型",
            "Java 注解",
            "Java 反射",
            "Java I/O",
            "Java 序列化",
        )
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> startActivity(JavaCollectionActivity::class.java)
            1 -> startActivity(JavaGenericActivity::class.java)
        }
    }


}