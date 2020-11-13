package com.hulk.androidstudy.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.hulk.androidstudy.R
import kotlinx.android.synthetic.main.activity_open.*

/**
 * Created by tzh on 2020/11/13.
 */
class OpenActivity : ComponentActivity(R.layout.activity_open) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent?.type == "text/plain") {
            tv_data.text = intent?.getStringExtra("data")
        }
        tv_data.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data", "数据已收到，返回结果")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}