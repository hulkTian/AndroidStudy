package com.hulk.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hulk.common.base.BaseFragment
import com.hulk.common.constants.Constants
import com.hulk.common.loadsir.ErrorCallback
import com.hulk.common.loadsir.LoadingCallback
import com.hulk.webview.databinding.FragmentWebviewBinding
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener


class WebViewFragment : BaseFragment(), OnRefreshListener, WebViewCallBack {
    private lateinit var mBinding: FragmentWebviewBinding
    private var url: String = ""
    private var canNativeRefresh: Boolean = false
    private lateinit var mLoadService: LoadService<*>
    private var mIsError = false
    private val TAG = "WebViewFragment"

    companion object {
        fun newInstance(url: String, canNativeRefresh: Boolean): WebViewFragment {
            val fragment = WebViewFragment()
            val bundle = Bundle()
            bundle.putString(Constants.URL, url)
            bundle.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString(Constants.URL)!!
        canNativeRefresh = arguments?.getBoolean(Constants.CAN_NATIVE_REFRESH, false)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        mBinding.webView.registerWebViewCallBack(this)
        mBinding.webView.loadUrl(url)
        mLoadService = LoadSir.getDefault().register(mBinding.srf) {
            mLoadService.showCallback(LoadingCallback::class.java)
            mBinding.webView.reload()
        }
//        BuildWebSettings.webSettings.setSettings(mBinding.webView)
//        mBinding.webView.webViewClient = MyWebViewClient(this)
//        mBinding.webView.webChromeClient = MyWebChromeClient(this)
        return mLoadService.loadLayout
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        super.initView()
        mBinding.webView.settings.javaScriptEnabled = true
        mBinding.srf.isEnableRefresh = canNativeRefresh
        mBinding.srf.isEnableLoadMore = false
        mBinding.srf.setOnRefreshListener(this)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
         mBinding.webView.reload()
    }

    override fun pageStarted(url: String?) {
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun pageFinished(url: String?) {
        if (mIsError) {
            mBinding.srf.isEnableRefresh = true
        } else {
            mBinding.srf.isEnableRefresh = canNativeRefresh
        }
        Log.d(TAG, "pageFinished")
        mBinding.srf.finishRefresh()
        if (mIsError) {
            mLoadService.showCallback(ErrorCallback::class.java)
        } else {
            mLoadService.showSuccess()
        }
        mIsError = false
    }

    override fun onError() {
        Log.e(TAG, "onError")
        mIsError = true
        mBinding.srf.finishRefresh()
    }

    override fun updateTitle(title: String?) {
        if (activity is WebViewActivity && title != null) {
            (activity as WebViewActivity).updateTitle(title)
        }
    }
}