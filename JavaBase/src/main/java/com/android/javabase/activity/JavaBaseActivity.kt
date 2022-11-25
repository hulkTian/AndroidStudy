package com.android.javabase.activity

import com.android.javabase.R
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseDataBean
import com.hulk.common.bean.BaseItemBean

class JavaBaseActivity : CommonMenuListActivity() {

    override fun getListData(): ArrayList<BaseItemBean> = arrayListOf(
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java 容器")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java 泛型")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java 注解")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java 反射")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java I/O")),
        BaseItemBean(R.layout.adapter_main, BaseDataBean("Java 序列化")),
    )

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        JavaCollectionActivity::class.java, JavaGenericActivity::class.java
    )

}