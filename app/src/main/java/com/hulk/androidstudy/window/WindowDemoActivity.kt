package com.hulk.androidstudy.window

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hulk.androidstudy.R

/**
 * created by tzh on 2021/12/7
 */
class WindowDemoActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        /*tv_demo.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }*/
    }


}