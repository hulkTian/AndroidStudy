package com.hulk.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Created by tzh on 2020/5/14.
 * fragment的懒加载主要是保证只有fragment对用户可见时才加载数据
 */
abstract class BaseFragment : Fragment() {
    private var isFirstResume = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isFirstResume == 0) {
            initView()
            initListener()
        }
    }

    override fun onResume() {
        super.onResume()
        //Fragment 的添加方式必须为BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT时才有效
        if (isFirstResume == 0 && !isHidden) {
            isFirstResume = 1
            initData()
        }
    }

    protected open fun initView() {}

    protected open fun initData() {}

    protected open fun initListener() {}

}