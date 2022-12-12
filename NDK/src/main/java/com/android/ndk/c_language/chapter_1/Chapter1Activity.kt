package com.android.ndk.c_language.chapter_1

import com.android.ndk.c_language.chapter_1.data.Chapter1DataProvider
import com.hulk.common.base.BaseListActivity
import com.hulk.common.bean.BaseItemBean

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/12/7
 */
class Chapter1Activity:BaseListActivity() {

    override fun initDataProvider() {
        mDataProvider = Chapter1DataProvider()
    }

    override fun getListData(): MutableList<BaseItemBean> = mDataProvider.getPageData()

}