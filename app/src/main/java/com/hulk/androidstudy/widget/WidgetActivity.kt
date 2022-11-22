package com.hulk.androidstudy.widget

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hulk.androidstudy.MainActivity
import com.hulk.androidstudy.R
import com.hulk.androidstudy.databinding.ActivityWidgetBinding

class WidgetActivity : Activity() {
    private lateinit var mBinding:ActivityWidgetBinding
    private val listData = arrayListOf("绘制文字", "点赞控件", "SeekBar", "自定义ViewGroup", "FlowLayout")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_widget)
        mBinding.rvList.layoutManager = LinearLayoutManager(this)
        mBinding.rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mBinding.rvList.adapter =
                MainActivity.Adapter(this, listData, object : MainActivity.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        when (position) {
                            0 -> startActivity(MyTextViewActivity::class.java)
                            1 -> startActivity(GiveLikeViewActivity::class.java)
                            2 -> startActivity(SeekBarActivity::class.java)
                            3 -> startActivity(CustomLayoutActivity::class.java)
                            4 -> startActivity(FlowLayoutActivity::class.java)
                        }
                    }
                })
    }

    private fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }
}