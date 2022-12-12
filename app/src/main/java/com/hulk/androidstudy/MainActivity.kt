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
import com.android.ndk.main.NDKActivity
import com.android.open.activity.OpenSourceActivity
import com.android.performance.activity.PerformanceActivity
import com.android.view.activity.ViewActivity
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants
import kotlin.collections.ArrayList

class MainActivity : CommonMenuListActivity() {

    override fun getListData(): MutableList<BaseItemBean> = arrayListOf(
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java基础"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java并发编程"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Java网络编程"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Kotlin语言"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "算法与数据结构"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "java/android虚拟机"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Android View框架体系"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Android Framework"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Android 性能优化"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Android 开源框架"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Android Jetpack"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Android NDK"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Gradle")
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