package com.hulk.webview.webview_process

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.google.gson.Gson
import com.hulk.webview.WebViewCallBack
import com.hulk.webview.bean.JsParam


class BaseWebView : WebView {
    val TAG = "HulkWebView"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr, 0
    )

    @SuppressLint("JavascriptInterface")
    constructor (
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        WebViewProcessCommandDispatcher.instance.initAidlConnection()
        BuildWebSettings.webSettings.setSettings(this)
        addJavascriptInterface(this, "hulkwebview")
    }

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack?) {
        webViewClient = MyWebViewClient(webViewCallBack)
        webChromeClient = MyWebChromeClient(webViewCallBack)
    }

    @JavascriptInterface
    fun takeNativeAction(jsParam: String?) {
        Log.i(TAG, jsParam ?: "jsParam is null")
        if (!TextUtils.isEmpty(jsParam)) {
            val jsParamObject = Gson().fromJson(jsParam, JsParam::class.java)
            if (jsParamObject != null) {
                WebViewProcessCommandDispatcher.instance
                    .executeCommand(jsParamObject.name, Gson().toJson(jsParamObject.param), this)
            }
        }
    }

    /**
     * 处理main进程返回的callback
     */
    fun handleCallback(callbackName: String, response: String) {
        if (!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)) {
            post {
                val jscode = "javascript:xiangxuejs.callback('$callbackName',$response)"
                Log.e("xxxxxx", jscode)
                evaluateJavascript(jscode, null)
            }
        }
    }


}