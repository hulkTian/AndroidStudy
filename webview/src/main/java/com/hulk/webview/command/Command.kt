package com.hulk.webview.command

import com.hulk.webview.ICallbackFromMainprocessToWebViewProcessInterface

interface Command {
    fun name(): String

    fun execute(
        parameters: Map<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    )

}