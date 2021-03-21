package com.hulk.webview.mian_process

import com.google.gson.Gson
import com.hulk.webview.ICallbackFromMainprocessToWebViewProcessInterface
import com.hulk.webview.IWebviewProcessToMainProcessInterface
import com.hulk.webview.command.Command
import java.util.*
import kotlin.collections.HashMap


/**
 * 主线程命令管理类，
 */
class MainProcessCommandsManager : IWebviewProcessToMainProcessInterface.Stub() {
    private val mCommands: HashMap<String, Command> = HashMap()

    init {//初始化时，遍历项目中所有的Command类型，并保存，方便之后执行命令
        val serviceLoader: ServiceLoader<Command> = ServiceLoader.load(Command::class.java)
        for (command in serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands[command.name()] = command
            }
        }
    }

    companion object {
        val instant = MainProcessCommandsManager()
    }

    /**
     * 解析后执行命令
     */
    private fun executeCommand(
        commandName: String?,
        params: MutableMap<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface
    ) {
        mCommands[commandName]?.execute(params, callback)
//        when (commandName) {
//            "openPage" -> {
//                val targetClass: String? = params["target_class"] as String?
//                if (!targetClass.isNullOrEmpty()) {
//                    val intent = Intent()
//                    intent.component = ComponentName(BaseApplication.application, targetClass)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    BaseApplication.application.startActivity(intent)
//                }
//            }
//        }
    }

    /**
     * 处理命令
     */
    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface
    ) {
        executeCommand(commandName, Gson().fromJson(jsonParams, MutableMap::class.java), callback)
    }
}