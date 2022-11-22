package com.hulk.androidstudy.widget

import com.hulk.common.base.CommonListActivity

class WidgetActivity : CommonListActivity() {

    override fun getListData(): ArrayList<String> =
        arrayListOf("绘制文字", "点赞控件", "SeekBar", "自定义ViewGroup", "FlowLayout")

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> startActivity(MyTextViewActivity::class.java)
            1 -> startActivity(GiveLikeViewActivity::class.java)
            2 -> startActivity(SeekBarActivity::class.java)
            3 -> startActivity(CustomLayoutActivity::class.java)
            4 -> startActivity(FlowLayoutActivity::class.java)
        }
    }
}