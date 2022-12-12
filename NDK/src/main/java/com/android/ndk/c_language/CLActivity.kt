package com.android.ndk.c_language

import com.android.ndk.c_language.chapter_1.Chapter1Activity
import com.android.ndk.c_language.chapter_2.Chapter2Activity
import com.android.ndk.c_language.chapter_3.Chapter3Activity
import com.android.ndk.c_language.chapter_4.Chapter4Activity
import com.android.ndk.c_language.chapter_5.Chapter5Activity
import com.android.ndk.c_language.chapter_6.Chapter6Activity
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/12/6
 */
class CLActivity : CommonMenuListActivity() {

    override fun getListData(): MutableList<BaseItemBean> = mutableListOf(
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "C语言简介"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "函数指针和指针计算"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "栈区和堆区空间的开辟"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "指针操作--字符串操作"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "结构体与结构体指针数组"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "文件加解密"),
    )

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        Chapter1Activity::class.java,
        Chapter2Activity::class.java,
        Chapter3Activity::class.java,
        Chapter4Activity::class.java,
        Chapter5Activity::class.java,
        Chapter6Activity::class.java,
    )
}