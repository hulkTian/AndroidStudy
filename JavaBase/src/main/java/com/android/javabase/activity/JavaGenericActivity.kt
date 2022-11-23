package com.android.javabase.activity

import com.android.javabase.R
import com.android.javabase.databinding.ActivityJavaGenericBinding
import com.hulk.common.base.BaseActivity

/**
 * 泛型知识
 */
class JavaGenericActivity : BaseActivity<ActivityJavaGenericBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_java_generic

}