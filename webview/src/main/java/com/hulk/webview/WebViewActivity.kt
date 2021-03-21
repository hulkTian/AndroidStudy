package com.hulk.webview

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.hulk.common.constants.Constants
import com.hulk.webview.databinding.ActivityWebviewBinding

class WebViewActivity : FragmentActivity() {
    private lateinit var mBinding: ActivityWebviewBinding
    private lateinit var url: String
    private var isShowTitle = false
    private var canNativeRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = intent.getStringExtra(Constants.URL) ?: ""
        isShowTitle = intent.getBooleanExtra(Constants.IS_SHOW_TITLE, false)
        canNativeRefresh = intent.getBooleanExtra(Constants.CAN_NATIVE_REFRESH, true)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        setTitle()
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = WebViewFragment.newInstance(url, canNativeRefresh)
        transaction.add(R.id.web_view_fragment, fragment)
            .setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
            .commit()
    }

    private fun setTitle() {
        if (isShowTitle) {
            mBinding.ivBack.visibility = View.VISIBLE
            mBinding.tvTitle.visibility = View.VISIBLE
            mBinding.ivBack.setOnClickListener {
                finish()
            }
        } else {
            mBinding.ivBack.visibility = View.GONE
            mBinding.tvTitle.visibility = View.GONE
        }
    }

    fun updateTitle(title: String) {
         mBinding.tvTitle.text = title
    }
}