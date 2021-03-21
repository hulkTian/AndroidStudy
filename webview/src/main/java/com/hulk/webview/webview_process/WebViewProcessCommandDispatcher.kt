package com.hulk.webview.webview_process

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.hulk.common.base.BaseApplication
import com.hulk.webview.ICallbackFromMainprocessToWebViewProcessInterface
import com.hulk.webview.IWebviewProcessToMainProcessInterface
import com.hulk.webview.mian_process.MainProcessCommandService


/**
 * 命令分配类
 */
class WebViewProcessCommandDispatcher : ServiceConnection {
    private var iWebviewProcessToMainProcessInterface: IWebviewProcessToMainProcessInterface? = null

    companion object {
        var instance = WebViewProcessCommandDispatcher()
    }

    /**
     * 启动Service，初始化连接
     */
    fun initAidlConnection() {
        val intent = Intent(BaseApplication.application, MainProcessCommandService::class.java)
        BaseApplication.application.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        iWebviewProcessToMainProcessInterface =
            IWebviewProcessToMainProcessInterface.Stub.asInterface(service)
    }

    /**
     * 连接中断，重新连接
     */
    override fun onServiceDisconnected(name: ComponentName?) {
        iWebviewProcessToMainProcessInterface = null
        initAidlConnection()
    }

    /**
     * 连接被销毁，重新连接
     */
    override fun onBindingDied(name: ComponentName?) {
        super.onBindingDied(name)
        iWebviewProcessToMainProcessInterface = null
        initAidlConnection()
    }

    /**
     * 执行命令
     */
    fun executeCommand(commandName: String?, params: String?, baseWebView: BaseWebView) {
        if (iWebviewProcessToMainProcessInterface != null) {
            iWebviewProcessToMainProcessInterface!!.handleWebCommand(commandName, params,
                object : ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
                    override fun onResult(callbackName: String, response: String) {
                        baseWebView.handleCallback(callbackName, response)
                    }
                })
        }
    }
}