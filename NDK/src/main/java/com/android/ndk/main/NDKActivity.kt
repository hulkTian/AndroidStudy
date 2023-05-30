package com.android.ndk.main

import com.android.ndk.c_language.CLActivity
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class NDKActivity : CommonMenuListActivity() {

    override fun getListData(): MutableList<BaseItemBean> = mutableListOf(
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "C语言"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "C++语言"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "JNI"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Linux系统"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "Shell脚本编成"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "FFmpeg交叉编译与集成"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "CMake构建工具"),
    )

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        CLActivity::class.java,
        CPlusLActivity::class.java,
        JNIActivity::class.java,
        LinuxActivity::class.java,
        ShellActivity::class.java,
        FFmpegActivity::class.java,
        CMakeActivity::class.java,
    )
}