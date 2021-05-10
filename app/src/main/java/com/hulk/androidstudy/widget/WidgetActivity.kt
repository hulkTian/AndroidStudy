package com.hulk.androidstudy.widget

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hulk.androidstudy.MainActivity
import com.hulk.androidstudy.R
import kotlinx.android.synthetic.main.activity_widget.*

class WidgetActivity : Activity() {
    private val listData = arrayListOf("绘制文字", "点赞控件", "SeekBar")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_list.adapter =
                MainActivity.Adapter(this, listData, object : MainActivity.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        when (position) {
                            0 -> startActivity(MyTextViewActivity::class.java)
                            1 -> startActivity(GiveLikeViewActivity::class.java)
                            2 -> startActivity(SeekBarActivity::class.java)
                        }
                    }
                })
    }

    private fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }
}