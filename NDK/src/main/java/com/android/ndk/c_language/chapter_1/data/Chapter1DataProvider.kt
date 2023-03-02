package com.android.ndk.c_language.chapter_1.data

import com.android.ndk.R
import com.android.ndk.c_language.chapter_1.NativeChapterOne
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.adapter.provider.DescItemProvider
import com.hulk.common.adapter.provider.RunItemProvider
import com.hulk.common.adapter.provider.TitleItemProvider
import com.hulk.common.bean.BaseDataProvider
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/12/7
 */
class Chapter1DataProvider : BaseDataProvider() {
    //native方法访问入库类
    private val nativeChapterOne = NativeChapterOne()

    override fun getPageData(): MutableList<BaseItemBean> = mutableListOf(
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_TITLE, "C文件结构"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_DESC, imgRes = R.drawable.c_one),
        BaseItemBean(
            ItemTypeConstants.ITEM_TYPE_RUN, nativeChapterOne.javaClass.name, method = "runMain"
        ),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_TITLE, "指针与内存地址"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_DESC, "1. *：取某地址的值（数据内容），运算符后通常跟一个地址"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_DESC, "2. &：取某数据（变量）的地址，运算符后通常跟一个变量"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_DESC, "3. 普通变量：存放数据内容，普通变量名==数据"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_DESC, "4. 一级指针变量：存放数据内容所在内存地址的值，指针变量名==地址"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_DESC, "5. 二级指针变量：存放一级指针变量所在内存地址的值，二级指针变量名==一级指针变量内存地址"),
        BaseItemBean(
            ItemTypeConstants.ITEM_TYPE_RUN, nativeChapterOne.javaClass.name, method = "pointAndAddress"
        ),
    )

    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> = mutableListOf(
        TitleItemProvider(),
        DescItemProvider(),
        RunItemProvider(),
    )
}