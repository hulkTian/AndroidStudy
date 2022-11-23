package com.android.framework.activity

import com.android.framework.R
import com.android.framework.databinding.ActivityContentProviderBinding
import com.hulk.common.base.BaseActivity

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class ContentProviderActivity : BaseActivity<ActivityContentProviderBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_content_provider
}