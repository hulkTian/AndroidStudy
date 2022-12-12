package com.hulk.common.utils

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import com.hulk.common.base.BaseApplication

/**
 * Created by tzh on 2021/3/24.
 */
object UiUtils {

    fun dp2px(dipValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun getApplication(): Context = BaseApplication.application

    fun getString(id: Int): String = getApplication().getString(id)

    fun showToast(content: String) {
        Toast.makeText(getApplication(), content, Toast.LENGTH_LONG).show()
    }
}