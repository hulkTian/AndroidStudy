package com.hulk.androidstudy

import com.android.algorithms.activity.AlgorithmsAndStructActivity
import com.android.concurrent.activity.JavaConcurrentActivity
import com.android.framework.activity.FrameworkActivity
import com.android.gradle.activity.GradleActivity
import com.android.javabase.R
import com.android.javabase.activity.JavaBaseActivity
import com.android.jetpack.activity.JetpackActivity
import com.android.jvm.activity.JVMActivity
import com.android.kotlin.activity.KotlinActivity
import com.android.network.activity.JavaNetworkActivity
import com.android.ndk.activity.NDKActivity
import com.android.open.activity.OpenSourceActivity
import com.android.performance.activity.PerformanceActivity
import com.android.view.activity.ViewActivity
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseDataBean
import com.hulk.common.bean.BaseItemBean
import kotlin.collections.ArrayList

class MainActivity : CommonMenuListActivity() {

    override fun getListData(): ArrayList<BaseItemBean> = arrayListOf(
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java基础")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java并发编程")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java网络编程")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Kotlin语言")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("算法与数据结构")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("java/android虚拟机")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Android View框架体系")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Android Framework")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Android 性能优化")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Android 开源框架")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Android Jetpack")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Android NDK")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Gradle"))
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