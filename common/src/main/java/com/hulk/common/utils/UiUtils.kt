package com.hulk.common.utils

import android.content.res.Resources

/**
 * Created by tzh on 2021/3/24.
 */
object UiUtils {

    fun dp2px(dipValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }
}