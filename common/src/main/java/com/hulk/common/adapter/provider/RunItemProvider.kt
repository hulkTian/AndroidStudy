package com.hulk.common.adapter.provider

import android.util.Log
import android.widget.TextView
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hulk.common.R
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.ItemTypeConstants
import com.hulk.common.utils.UiUtils

/**
 * @description 代码运行，展示结果。通过反射拿到类的实例和方法运行
 * @author: zehao.tian
 * @date: 2022/12/12
 */
class RunItemProvider(
    override val itemViewType: Int = ItemTypeConstants.ITEM_TYPE_RUN,
    override val layoutId: Int = R.layout.item_provider_run
) : BaseItemProvider<BaseItemBean>() {

    override fun convert(helper: BaseViewHolder, item: BaseItemBean) {
        val tvRun = helper.getView<TextView>(R.id.tv_run)
        tvRun.setOnClickListener {
            clickRun(item)
        }
    }

    private fun clickRun(item: BaseItemBean) {
        if (item.method.isNullOrEmpty()) {
            Log.d("RunItemProvider", "class name is null")
            return
        } else {
            Log.d("RunItemProvider", "class name is " + item.title)
        }
        if (item.method.isNullOrEmpty()) {
            Log.d("RunItemProvider", "method name is null")
            return
        } else {
            Log.d("RunItemProvider", "method name is " + item.method)
        }
        //传入全限定名，得到类对象
        val clazz = javaClass.classLoader?.loadClass(item.title)
        if (clazz == null) {
            Log.d("RunItemProvider", "class is not find")
            return
        } else {
            Log.d("RunItemProvider", "class is find")
        }
        //反射获取类的实力
        val obj = clazz.newInstance()
        if (obj == null) {
            Log.d("RunItemProvider", "obj is null")
            return
        } else {
            Log.d("RunItemProvider", "obj is not null")
        }
        //反射调用类的方法
        val method = clazz.getDeclaredMethod(item.method)
        UiUtils.showToast(method.invoke(obj) as String)
    }
}