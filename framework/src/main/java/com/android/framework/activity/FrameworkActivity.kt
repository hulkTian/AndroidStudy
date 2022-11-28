package com.android.framework.activity

import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class FrameworkActivity : CommonMenuListActivity() {

    override fun getListData(): MutableList<BaseItemBean> = arrayListOf(
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Binder进程间通信"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Activity启动过程"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Service启动过程"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "广播机制"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "ContentProvider实现原理"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "zygote和system进程启动过程"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "应用程序进程启动过程"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "应用程序线程的消息循环机制"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "应用程序的安装和显示过程"),
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