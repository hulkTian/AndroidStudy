package com.android.framework.activity

import com.hulk.common.base.CommonListActivity

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class FrameworkActivity : CommonListActivity() {

    override fun getListData(): ArrayList<String> = arrayListOf(
        "Binder进程间通信", "Activity启动过程", "Service启动过程", "广播机制", "Content Provider实现原理",
        "zygote和system进程启动过程", "应用程序进程启动过程", "应用程序线程的消息循环机制",
        "应用程序的安装和显示过程"
    )

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        BinderActivity::class.java,
        ActivityStartProcessActivity::class.java,
        ServiceStartProcessActivity::class.java,
        BroadcastActivity::class.java,
        ContentProviderActivity::class.java,
        ZygoteAndSystemActivity::class.java,
        AppStartProcessActivity::class.java,
        AppMessageLopperActivity::class.java,
        AppInstallAndResumeActivity::class.java,
    )

}