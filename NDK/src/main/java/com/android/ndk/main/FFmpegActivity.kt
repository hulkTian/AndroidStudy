package com.android.ndk.main

import com.android.ndk.main.ffmpeg.FFmpegMediaActivity
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants

/**
 * @description ffmpeg框架学习
 * @author: zehao.tian
 * @date: 2022/12/6
 */
class FFmpegActivity : CommonMenuListActivity() {

    override fun getListData(): MutableList<BaseItemBean> = mutableListOf(
        BaseItemBean(ItemTypeConstants.ITEM_TYPE_MENU, "ffmpeg 播放器")
    )

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        FFmpegMediaActivity::class.java
    )
}