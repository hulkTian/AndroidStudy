package com.hulk.androidstudy.widget

import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean

class WidgetActivity : CommonMenuListActivity() {

    override fun getListData(): ArrayList<BaseItemBean> =
        arrayListOf(/*"绘制文字", "点赞控件", "SeekBar", "自定义ViewGroup", "FlowLayout"*/)

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        MyTextViewActivity::class.java,
        GiveLikeViewActivity::class.java,
        SeekBarActivity::class.java,
        CustomLayoutActivity::class.java,
        FlowLayoutActivity::class.java
    )

}