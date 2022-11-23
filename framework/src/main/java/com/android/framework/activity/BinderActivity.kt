package com.android.framework.activity

import com.android.framework.R
import com.android.framework.databinding.ActivityBinderBinding
import com.hulk.common.base.BaseActivity

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class BinderActivity: BaseActivity<ActivityBinderBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_binder
}