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
class Chapter1DataProvider: BaseDataProvider() {
    //native方法访问入库类
    private val nativeChapterOne = NativeChapterOne()

    override fun getPageData(): MutableList<BaseItemBean> = mutableListOf(
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_TITLE, "C文件结构"),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_DESC, imgRes = R.mipmap.c_one),
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_RUN, nativeChapterOne.javaClass.name, method = "runMain")
    )

    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> = mutableListOf(
        TitleItemProvider(),
        DescItemProvider(),
        RunItemProvider(),
    )
}