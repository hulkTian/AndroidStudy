package com.android.javabase.activity

import com.android.javabase.R
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseDataBean
import com.hulk.common.bean.BaseItemBean

class JavaCollectionActivity : CommonMenuListActivity() {

    override fun getListData(): ArrayList<BaseItemBean> {
        return arrayListOf(
            /*"ArrayList",
            "Java LinkedList",
            "Java 注解",
            "Java 反射",
            "Java I/O",
            "Java 序列化",*/
            BaseItemBean(R.layout.adapter_main, BaseDataBean("ArrayList")),
            BaseItemBean(R.layout.adapter_main, BaseDataBean("LinkedList")),
        )
    }

    override fun getActivityListData(): ArrayList<Class<*>> {
        TODO("Not yet implemented")
    }

}