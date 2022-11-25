package com.android.framework.activity

import com.android.framework.R
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseDataBean
import com.hulk.common.bean.BaseItemBean

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class FrameworkActivity : CommonMenuListActivity() {

    override fun getListData(): ArrayList<BaseItemBean> = arrayListOf(
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Binder进程间通信")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Activity启动过程")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Service启动过程")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("广播机制")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("ContentProvider实现原理")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("zygote和system进程启动过程")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("应用程序进程启动过程")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("应用程序线程的消息循环机制")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("应用程序的安装和显示过程")),
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