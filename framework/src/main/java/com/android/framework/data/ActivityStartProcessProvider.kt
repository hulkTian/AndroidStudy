package com.android.framework.data

import com.android.framework.R
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.adapter.provider.DescItemProvider
import com.hulk.common.adapter.provider.TabItemProvider
import com.hulk.common.adapter.provider.TitleItemProvider
import com.hulk.common.bean.BaseDataProvider
import com.hulk.common.bean.BaseItemBean

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/12/1
 */
class ActivityStartProcessProvider : BaseDataProvider() {
    override fun getPageData(): MutableList<BaseItemBean> {
        dataList.add(buildTitleItemBean(R.string.activity_start_process))
        dataList.add(buildDescItemBean(R.string.activity_start_process_description))
        dataList.add(buildTabFirstItemBean("调用方法", "作用描述"))
        dataList.add(
            buildTabItemBean(
                "（1）LauncherActivity#startActivitySafely",
                "为Intent对象添加Intent.FLAG_ACTIVITY_NEW_TASK标志位"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（2）Activity#startActivity",
                "此方法表示Launcher组件不用知道将启动的Activity组件的执行结果。即设置result = -1"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（3）Activity#startActivityForResult",
                "调用Instrumentation的方法"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（4）Instrumentation#execStartActivity",
                "完成监控逻辑后，通过AMS的代理对象调用AMS的方法"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（5）ActivityManagerProxy#startActivity",
                "发起进程间通信请求START_ACTIVITY_TRANSACTION"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（6）AMS#startActivity", "调用ActivityStack的方法，处理进程间通信请求"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（7）ActivityStack#startActivityMayWait",
                "通过PMS解析intent参数得到将要启动组件的信息并保存在ActivityInfo对象aInfo中"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（8）ActivityStack#startActivityLocked",
                "1. 获取启动进程信息并保存起来。\n"
                        + "2. 在AMS中找到Launcher组件对应的ActivityRecord对象\n"
                        + "3. 创建一个ActivityRecord对象描述目标Activity组件"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（9）ActivityStack#startActivityUncheckedLocked",
                "1. 从目标ActivityRecord得到启动标志位\n" +
                        "2. 根据标志位、taskAffinity属性和源Activity是否需要知道目标Activity组件的运行结果，" +
                        "判断目标Activity应该放在哪个任务中运行\n" +
                        "3. 如果需要创建新的任务，就先创建一个新的TaskRecord"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（10）ActivityStack#resumeTopActivityLocked",
                "1. 获取当前TaskRecord最上面的一个不是处于结束状态的Activity组件\n" +
                        "2. 如果当前要激活的Activity就是栈顶resume状态的Activity，就返回，" +
                        "表示要启动的Activity已经启动和激活了\n" +
                        "3. 上次中止的Activity等于当前要激活的Activity并且处于pause状态并且系统正要关机或睡眠状态，" +
                        "直接返回，这时启动Activity没有意义\n" +
                        "4. 如果当前有正在中止的Activity，需要等待它中止完成后，再启动Activity组件next，也直接返回\n" +
                        "5. 当前有resume的组件，当前就是Launcher组件，通知它进入pause状态，" +
                        "以便将焦点让给要启动的MainActivity组件"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（11）ActivityStack#startPausingLocked",
                "1. 设置三个ActivityRecord变量的值，mLastPausedActivity和mPausingActivity指向要进入Pause状态的Launcher组件\n" +
                        "2. 向Launcher组件所在进程发送中止通知\n" +
                        "3. Launcher组件所在进程完成一些数据保存操作后，回复AMS的中止通知，发送一个启动MainActivity的通知\n" +
                        "4. AMS在发完中止通知后，会发送一个延迟执行消息，如果Launcher组件不能在指定时间发送启动" +
                        "MainActivity的通知，AMS就会认为Launcher没有响应了"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（12）ApplicationThreadProxy#schedulePauseActivity",
                "AMS向Launcher发送进程间通信请求"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（13）ApplicationThread#schedulePauseActivity",
                "处理进程间通信请求，判断要发送的消息类型"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（14）ApplicationThread#queueOrSendMessage", "创建消息，发送消息给主线程"
            )
        )
        dataList.add(buildTabItemBean("（15）H#handleMessage", "切换到主线程，处理消息"))
        dataList.add(
            buildTabItemBean(
                "（16）ActivityThread#handlePauseActivity",
                "1. 根据token获取ActivityThread保存的ActivityClientRecord对象\n" +
                        "2. 使用ActivityClientRecord告知Launcher对应Activity组件，用户离开事件通知\n" +
                        "3. 向Launcher组件发送一个中止事件通知，即调用它的成员函数onPause\n" +
                        "4. 等待完成前面的一些数据写入操作\n" +
                        "5. 回复AMS，Launcher组件已经进入Pause状态了"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（17）ActivityManagerProxy#activityPaused",
                "1. 参数传递的数据写入到Parcel\n" +
                        "2. 通过Binder代理对象向AMS发送一个类型为ACTIVITY_PAUSED_TRANSACTION的进程间通信请求"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（18）AMS#activityPaused",
                "开始处理ACTIVITY_PAUSED_TRANSACTION的进程间通信请求"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（19）ActivityStack#activityPaused",
                "1. 用token得到Launcher对应的ActivityRecord对象\n" +
                        "2. 把要启动的组件信息设置给Launcher对应的ActivityRecord对象\n" +
                        "3. 设置ActivityRecord对象的状态为Pause\n" +
                        "4. 移除之前发送的超时消息"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（20）ActivityStack#completePauseLocked",
                "1. 把mPausingActivity设置为null\n" +
                        "2. 如果当前不是正在进入睡眠或关闭状态，开始启动位置堆栈顶端的Activity组件"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（21）ActivityStack#resumeTopActivityLocked",
                "第10步调用过此方法来Pause Launcher组件，这里再次调用Launcher组件已经Pause，就会继续向下执行"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（22）ActivityStack#startSpecificActivityLocked",
                "1. 在AMS中检查应用进程对应的ProcessRecord对象是否存在\n" +
                        "2. 存在，开始启动Activity\n" +
                        "3. 不存在，开始启动对应的应用程序进程（MainActivity第一次启动，走此步骤）"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（23）ActivityStack#startProcessLocked",
                "1. 根据进程名称和UID检查要创建的应用程序进程是否已经存在了\n" +
                        "2. 不存在，根据进程名称和UID创建一个ProcessRecord对象并保存在AMS的mProcessNames\n" +
                        "3. 创建应用程序进程\n" +
                        "  a. 创建用户ID和用户组ID\n" +
                        "  b. 启动一个新的应用程序进程\n" +
                        "  c. 启动进程是一个异步且耗时的操作，发送一个延迟消息检查启动工作是否超时"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（24）ActivityThread#main",
                "1. 创建一个ActivityThread对象，并向AMS发送一个启动完成通知\n" +
                        "2. 创建一个消息循环，等通知发送完成后，开启消息循环"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（25）ActivityManagerProxy#attachApplication",
                "构建进程间通信数据并发送一个类型为ATTACH_APPLICATION_TRANSACTION的进程间通信请求"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（26）AMS#attachApplication",
                "加同步锁，开始处理MainActivity启动"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（27）AMS#attachApplicationLocked",
                "1. 根据PID取出之前保存的ProcessRecord对象\n" +
                        "2. 将thread参数保存在ProcessRecord对象中，这样AMS就可以通过它来与应用程序进程通信\n" +
                        "3. 删除之前启动进程发送的延迟消息\n" +
                        "4. 获取保存在ActivityStack栈顶的ActivityRecord对象，它对应即将要启动的MainActivity组件\n" +
                        "5. 判断ActivityRecord对象中的UID和进程名称与ProcessRecord对象保存的数据是否一致\n" +
                        "6. 如果一致，说明ActivityRecord对象就是当前要启动的对象，开始在ActivityStack中启动Activity组件"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（28）ActivityStack#realStartActivityLocked",
                "1. 把参数app设置给r，并把r也保存在app的Activity组件列表中\n" +
                        "2. 使用参数app的成员变量thread来向应用程序进程发送一个进程间通信请求"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（29）ApplicationThreadProxy#realStartActivityLocked",
                "1. 将传递的参数写入到Parcel中\n" +
                        "2. 发送一个类型为SCHEDULE_LAUNCH_ACTIVITY_TRANSACTION的进程间通信请求"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（30）ApplicationThread#scheduleLaunchActivity",
                "将要启动的组件信息封装成一个ActivityClientRecord对象"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（31）ApplicationThread#queueOrSendMessage",
                "发送一个Handler消息LAUNCH_ACTIVITY在主线程处理Activity组件的启动，消息包含的数据就是ActivityClientRecord对象"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（32）H#handleMessage",
                "1. 强制Message对象的obj变量得到ActivityClientRecord对象\n" +
                        "2. 使用ActivityClientRecord对象保存的应用信息获得一个LoadedApk对象并保存在ActivityClientRecord对象中"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（33）ActivityThread#handleLaunchActivity",
                "启动Activity并设置为Resumed状态"
            )
        )
        dataList.add(
            buildTabItemBean(
                "（34）ActivityThread#performLaunchActivity",
                "1. 从参数r中获得Activity组件的包名和类名，并使用一个ComponentName对象描述\n" +
                        "2. 加载Activity对应的类文件并创建一个实例\n" +
                        "3. 创建一个ContextImpl对象作为Activity对象的运行上下文环境\n" +
                        "4. 使用ContextImpl对象和ActivityClientRecord对象初始化Activity实例\n" +
                        "5. 将ActivityClientRecord对象保存在ActivityThread类的成员变量mAtivities中"
            )
        )
        dataList.add(buildTabLastItemBean("（35）MainActivity#onCreate", "加载用户界面，初始化控件"))
        return dataList
    }

    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> = mutableListOf(
        TitleItemProvider(),
        DescItemProvider(),
        TabItemProvider(),
    )
}