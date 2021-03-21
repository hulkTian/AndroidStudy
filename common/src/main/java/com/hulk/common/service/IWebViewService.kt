package com.hulk.common.service

import android.content.Context
import androidx.fragment.app.Fragment

interface IWebViewService {
    fun startWebViewActivity(
        context: Context, url: String, isShowTitle: Boolean,
        canNativeRefresh: Boolean
    )

    fun getWebFragment(url: String, canNativeRefresh: Boolean): Fragment

    fun startDemoHtml(context: Context)

    fun isLogined(): Boolean

    fun login()
}