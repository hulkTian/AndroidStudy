package com.hulk.androidstudy.widget

import android.app.Activity
import android.os.Bundle
import android.widget.SeekBar
import com.hulk.androidstudy.R
import kotlinx.android.synthetic.main.activity_seek_bar.*

/**
 * Created by tzh on 2021/5/10.
 */
class SeekBarActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seek_bar)
        sb_scale.max = 5
        sb_scale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tv_scale.text = progress.toString()
                val xUnit = (sb_scale.width - sb_scale.paddingStart - sb_scale.paddingEnd) / sb_scale.max
                tv_scale.x = (xUnit * progress + tv_scale.width / 4).toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        sb_scale.progress = 0
    }
}