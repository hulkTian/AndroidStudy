package com.hulk.androidstudy.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.hulk.androidstudy.R
import kotlinx.android.synthetic.main.activity_get_result.*

/**
 * 获取Activity结果
 * Created by tzh on 2020/11/13.
 */
class GetResultActivity : ComponentActivity(R.layout.activity_get_result) {
    private val launcher = activityResultRegistry.register(
        "aaa",
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            tv_result.text = it.data!!.getStringExtra("key")
        }
    }

    private val getContent = activityResultRegistry.register(
        "getContent",
        ActivityResultContracts.GetContent()
    ) {
        tv_result.text = it.path
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bt_start_activity_for_result.setOnClickListener {
            launcher.launch(Intent(this, GetResultActivity::class.java), null)
        }
        bt_set_result.setOnClickListener {
            val intent = Intent()
            intent.putExtra("key", "输出结果:111111")
            setResult(RESULT_OK, intent)
            finish()
        }
        bt_get_content.setOnClickListener {
            getContent.launch("image/*")
        }
    }


}