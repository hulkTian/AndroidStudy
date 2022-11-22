package com.hulk.androidstudy.widget

import android.app.Activity
import android.os.Bundle
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import com.hulk.androidstudy.R
import com.hulk.androidstudy.databinding.ActivitySeekBarBinding

/**
 * Created by tzh on 2021/5/10.
 */
class SeekBarActivity : Activity() {
    private lateinit var mBinding : ActivitySeekBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_seek_bar)
        mBinding.sbScale.max = 5
        mBinding.sbScale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mBinding.tvScale.text = progress.toString()
                val xUnit = (mBinding.sbScale.width - mBinding.sbScale.paddingStart - mBinding.sbScale.paddingEnd) / mBinding.sbScale.max
                mBinding.tvScale.x = (xUnit * progress + mBinding.tvScale.width / 4).toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        mBinding.sbScale.progress = 0
    }
}