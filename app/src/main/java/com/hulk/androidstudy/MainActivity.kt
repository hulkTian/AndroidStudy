package com.hulk.androidstudy

import com.android.algorithms.activity.AlgorithmsAndStructActivity
import com.android.concurrent.activity.JavaConcurrentActivity
import com.android.framework.activity.FrameworkActivity
import com.android.gradle.activity.GradleActivity
import com.android.javabase.activity.JavaBaseActivity
import com.android.jetpack.activity.JetpackActivity
import com.android.jvm.activity.JVMActivity
import com.android.kotlin.activity.KotlinActivity
import com.android.network.activity.JavaNetworkActivity
import com.android.ndk.activity.NDKActivity
import com.android.open.activity.OpenSourceActivity
import com.android.performance.activity.PerformanceActivity
import com.android.view.activity.ViewActivity
import com.hulk.common.base.CommonListActivity
import kotlin.collections.ArrayList

class MainActivity : CommonListActivity() {

    override fun getListData(): ArrayList<String> = arrayListOf(
        "Java基础", "Java并发编程", "Java网络编程", "Kotlin语言", "算法与数据结构", "java/android虚拟机",
        "Android View框架体系", "Android Framework", "Android 性能优化", "Android 开源框架",
        "Android Jetpack", "Android NDK", "Gradle"
    )

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        JavaBaseActivity::class.java,
        JavaConcurrentActivity::class.java,
        JavaNetworkActivity::class.java,
        KotlinActivity::class.java,
        AlgorithmsAndStructActivity::class.java,
        JVMActivity::class.java,
        ViewActivity::class.java,
        FrameworkActivity::class.java,
        PerformanceActivity::class.java,
        OpenSourceActivity::class.java,
        JetpackActivity::class.java,
        NDKActivity::class.java,
        GradleActivity::class.java
    )

}