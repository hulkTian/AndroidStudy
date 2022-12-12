package com.android.framework.activity

import com.android.framework.data.ActivityStartProcessProvider
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.base.BaseListActivity
import com.hulk.common.bean.BaseItemBean

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class ActivityStartProcessActivity : BaseListActivity() {

    override fun initDataProvider() {
        mDataProvider = ActivityStartProcessProvider()
    }

}