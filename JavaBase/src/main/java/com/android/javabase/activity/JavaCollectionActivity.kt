package com.android.javabase.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.java_base.R
import com.android.java_base.databinding.ActivityJavaCollectionBinding

class JavaCollectionActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityJavaCollectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_java_collection)
    }
}