package com.android.javabase.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.javabase.R
import com.android.javabase.databinding.ActivityJavaGenericBinding

class JavaGenericActivity: AppCompatActivity() {
    private lateinit var mBinding : ActivityJavaGenericBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_java_generic)
    }
}