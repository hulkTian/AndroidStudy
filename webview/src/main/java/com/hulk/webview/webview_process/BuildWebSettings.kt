package com.hulk.webview.webview_process

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import com.hulk.webview.BuildConfig

class BuildWebSettings {

    companion object {
        val webSettings = BuildWebSettings()
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun setSettings(webView: WebView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw()
        }
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = false
        if (isNetworkConnected(webView.context)) {
            webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        } else {
            webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        // 硬件加速兼容性问题有点多
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
        webSettings.textZoom = 100
        webSettings.databaseEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.loadsImagesAutomatically = true
        webSettings.setSupportMultipleWindows(false)
        webSettings.blockNetworkImage = false //是否阻塞加载网络图片  协议http or https
        webSettings.allowFileAccess = true //允许加载本地文件html  file协议
        webSettings.allowFileAccessFromFileURLs = false  //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
        webSettings.allowUniversalAccessFromFileURLs = false  //允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webSettings.savePassword = false
        webSettings.saveFormData = false
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true
        webSettings.setNeedInitialFocus(true)
        webSettings.defaultTextEncodingName = "utf-8" //设置编码格式
        webSettings.defaultFontSize = 16
        webSettings.minimumFontSize = 10 //设置 WebView 支持的最小字体大小，默认为 8
        webSettings.setGeolocationEnabled(true)
        webSettings.useWideViewPort = true
        val appCacheDir = webView.context.getDir("cache", Context.MODE_PRIVATE).path
        Log.i("WebSetting", "WebView cache dir: $appCacheDir")
        webSettings.databasePath = appCacheDir
        webSettings.setAppCachePath(appCacheDir)
        webSettings.setAppCacheMaxSize(1024 * 1024 * 80)

        // 用户可以自己设置useragent
        // webSettings.setUserAgentString("webprogress/build you agent info");
        WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
    }

}