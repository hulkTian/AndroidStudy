package com.hulk.androidstudy.window

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.hulk.androidstudy.R

/**
 * created by tzh on 2021/12/7
 */
class MyService: Service() {
    private val  mHandler = Handler(Looper.getMainLooper())

    override fun onBind(intent: Intent?): IBinder? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mHandler.postDelayed({
            showWindow()
        }, 2000)
        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun showWindow() {
        val mWindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewRoot = layoutInflater.inflate(R.layout.dialog_window, null)
        val mLayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_INPUT_METHOD,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.RGBA_8888
        )
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        mLayoutParams.gravity = Gravity.CENTER
        mLayoutParams.x = 100
        mLayoutParams.y = 300
        mWindowManager.addView(viewRoot, mLayoutParams)
    }
}