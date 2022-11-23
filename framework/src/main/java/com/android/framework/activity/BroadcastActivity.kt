package com.android.framework.activity

import com.android.framework.R
import com.android.framework.databinding.ActivityBroadcastBinding
import com.hulk.common.base.BaseActivity

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class BroadcastActivity : BaseActivity<ActivityBroadcastBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_broadcast
}