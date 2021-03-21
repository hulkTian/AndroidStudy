package com.hulk.webview.mian_process

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * 主进程服务，被动接受webViewProcess通过aidl传递的命令
 */
class MainProcessCommandService: Service() {

    override fun onBind(intent: Intent?): IBinder {
        return MainProcessCommandsManager.instant
    }
}