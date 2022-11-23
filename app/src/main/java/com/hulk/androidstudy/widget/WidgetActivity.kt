package com.hulk.androidstudy.widget

import com.hulk.common.base.CommonListActivity

class WidgetActivity : CommonListActivity() {

    override fun getListData(): ArrayList<String> =
        arrayListOf("绘制文字", "点赞控件", "SeekBar", "自定义ViewGroup", "FlowLayout")

    override fun getActivityListData(): ArrayList<Class<*>> = arrayListOf(
        MyTextViewActivity::class.java,
        GiveLikeViewActivity::class.java,
        SeekBarActivity::class.java,
        CustomLayoutActivity::class.java,
        FlowLayoutActivity::class.java
    )

}