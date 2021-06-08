package com.hulk.androidstudy.mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hulk.androidstudy.R
import com.hulk.androidstudy.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)
    }
}