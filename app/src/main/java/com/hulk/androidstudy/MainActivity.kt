package com.hulk.androidstudy

import com.android.javabase.activity.JavaBaseActivity
import com.hulk.common.base.CommonListActivity
import kotlin.collections.ArrayList

class MainActivity : CommonListActivity() {

    override fun getListData(): ArrayList<String> = arrayListOf(
        "Java基础",
        "Java并发编程",
        "Java网络编程",
        "Kotlin语言",
        "算法与数据结构",
        "java/android虚拟机",
        "Android View框架体系",
        "Android View框架体系",
        "Android Framework",
        "Android 性能优化",
        "Android 开源框架",
        "Android Jetpack",
        "Android NDK",
        "webView框架",
        "Gradle"
    )

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> startActivity(JavaBaseActivity::class.java)
        }
    }


}