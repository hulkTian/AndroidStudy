package com.hulk.androidstudy.mvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hulk.androidstudy.databinding.FragmentNewsBinding
import com.hulk.common.base.BaseFragment
import com.hulk.webview.R

class NewsFragment: BaseFragment() {
    private lateinit var mBinding:FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}