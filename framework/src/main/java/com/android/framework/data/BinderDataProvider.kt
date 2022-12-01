package com.android.framework.data

import com.android.framework.R
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.hulk.common.adapter.provider.*
import com.hulk.common.bean.BaseDataProvider
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.utils.UiUtils

/**
 * Binder知识界面数据
 * created by tzh on 2022/11/28
 */
class BinderDataProvider : BaseDataProvider() {

    override fun getPageData(): MutableList<BaseItemBean> {
        //概述标题
        dataList.add(buildTitleItemBean(R.string.item_overview))
        //概述内容
        dataList.add(buildDescItemBean(R.string.binder_item_overview_description))
        //基础数据结构标题
        dataList.add(buildTitleItemBean(R.string.binder_title_struct))
        //基础数据结构表格第一行
        dataList.add(
            buildTabFirstItemBean(
                UiUtils.getString(R.string.item_struct_name),
                UiUtils.getString(R.string.item_effect)
            )
        )
        dataList.add(buildTabItemBean("binder_work", "描述待处理的工作项"))
        dataList.add(
            buildTabItemBean("binder_node", "描述一个Binder实体对象，表示一个Service组件")
        )
        dataList.add(
            buildTabItemBean("binder_ref", "描述一个Binder引用对象，表示一个Client组件")
        )
        dataList.add(
            buildTabItemBean("binder_ref_death", "描述一个Service组件的死亡接收通知")
        )
        dataList.add(
            buildTabItemBean("binder_buffer", "描述一个内核缓冲区，用来在进程间传递数据")
        )
        dataList.add(
            buildTabItemBean("binder_pro", "描述一个正在使用Binder进程间通信机制的进程")
        )
        dataList.add(buildTabItemBean("binder_thread", "描述Binder线程池中的一个线程"))
        dataList.add(
            buildTabItemBean(
                "binder_transaction", "描述进程间通信过程，这个过程称为一个事务"
            )
        )
        dataList.add(
            buildTabItemBean("binder_write_read", "描述进程间通信过程中所传输的数据")
        )
        dataList.add(
            buildTabItemBean("BinderDriverCommandProtocol", "枚举值来定义命令协议代码")
        )
        dataList.add(
            buildTabItemBean("BinderDriverReturnProtocol", "枚举值来定义返回协议代码")
        )
        dataList.add(
            buildTabItemBean(
                "binder_ptr_cookie",
                "描述一个Binder实体对象或者一个Service组件的死亡接收通知"
            )
        )
        dataList.add(
            buildTabItemBean("binder_transaction_data", "描述进程间通信过程中所传输的数据")
        )
        //基础数据结构表格最后一行
        dataList.add(
            buildTabLastItemBean(
                "flat_binder_object",
                "除了可以描述一个Binder实体对象和Binder引用对象外，还可以描述一个文件描述符，"
                        + "它们通过成员变量type来区分"
            )
        )
        //Binder设备的初始化过程
        dataList.add(buildTitleItemBean(R.string.binder_device_init))
        //Binder设备的初始化过程表格第一行
        dataList.add(buildTabFirstItemBean(UiUtils.getString(R.string.item_process)))
        dataList.add(buildTabItemBean("调用binder_init函数"))
        dataList.add(buildTabItemBean("在目标设备上创建一个/proc/binder/proc目录"))
        dataList.add(
            buildTabItemBean(
                "在目录下创建五个文件state、stats、transactions、" +
                        "transaction_log和failed_transaction_log"
            )
        )
        dataList.add(buildTabLastItemBean("调用misc_register来创建一个Binder设备"))
        //Binder设备的打开过程
        dataList.add(buildTitleItemBean(R.string.binder_device_open))
        //Binder设备的初始化过程表格第一行
        dataList.add(buildTabFirstItemBean(UiUtils.getString(R.string.item_process)))
        dataList.add(buildTabItemBean("调用open函数打开设备"))
        dataList.add(buildTabItemBean("open函数会调用binder_open函数"))
        dataList.add(buildTabItemBean("binder_open函数会创建binder_proc结构体并初始化"))
        dataList.add(buildTabLastItemBean("把binder_proc结构体保存在一个hash队列便于检索"))
        //Binder设备的内存映射过程
        dataList.add(buildTitleItemBean(R.string.binder_device_mmap))
        dataList.add(buildDescItemBean(R.string.binder_device_mmap_description))
        //内核缓冲区管理
        dataList.add(buildTitleItemBean(R.string.binder_buffer_manager))
        dataList.add(buildDescItemBean(R.string.binder_buffer_manager_description))
        return dataList
    }

    /**
     * 所有需要注册的Provider.
     */
    override fun getItemProviderData(): MutableList<BaseItemProvider<BaseItemBean>> = mutableListOf(
        TitleItemProvider(),
        DescItemProvider(),
        TabItemProvider(),
    )

}