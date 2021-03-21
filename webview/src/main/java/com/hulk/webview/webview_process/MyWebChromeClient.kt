package com.hulk.webview.webview_process

import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.hulk.webview.WebViewCallBack


class MyWebChromeClient(private val webViewBack: WebViewCallBack?) : WebChromeClient() {
    private val TAG = "MyWebChromeClient"

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        webViewBack?.updateTitle(title)
    }

    /**
     * 获取浏览器控制台消息
     */
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        // Call the old version of this function for backwards compatability.
        Log.d(TAG, consoleMessage.message())
        return super.onConsoleMessage(consoleMessage)
    }

}