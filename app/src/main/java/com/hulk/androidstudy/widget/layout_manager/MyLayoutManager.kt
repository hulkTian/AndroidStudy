package com.hulk.androidstudy.widget.layout_manager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hulk.common.utils.UiUtils

/**
 * created by tzh on 2021/9/4
 */
class MyLayoutManager() : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
        RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        //1.先进行布局的回收
        detachAndScrapAttachedViews(recycler)
        //2.计算最后一张图片的位置
        var bottomPosition = 0//最下面一张图片在列表中的位置
        val itemCount = itemCount
        bottomPosition = if (itemCount < CardConfig.maxCount) {
            0
        } else {
            itemCount - CardConfig.maxCount
        }
        //3.布局子View
        for (i in bottomPosition until itemCount) {
            //复用View
            val view = recycler.getViewForPosition(i)
            addView(view)

            measureChildWithMargins(view, 0, 0)
            //计算Item宽高
            val widthSpace = width - getDecoratedMeasuredWidth(view)
            val heightSpace = height - getDecoratedMeasuredWidth(view)
            //布局
            layoutDecoratedWithMargins(
                view,
                widthSpace / 2,
                heightSpace / 2,
                width + widthSpace / 2,
                height + heightSpace / 2
            )

            //根据层级进行缩放
            val level = itemCount - i - 1
            if (level > 0) {
                if (level < CardConfig.maxCount - 1) {
                    view.translationY = CardConfig.transitionY * level
                    view.scaleX = 1 - CardConfig.scaleGap * level
                    view.scaleY = 1 - CardConfig.scaleGap * level
                } else {
                    view.translationY = CardConfig.transitionY * (level - 1)
                    view.scaleX = 1 - CardConfig.scaleGap * (level - 1)
                    view.scaleY = 1 - CardConfig.scaleGap * (level - 1)
                }
            }
        }
    }

    object CardConfig {
        //最大显示数量
        const val maxCount = 4

        //相邻图片的缩放差值
        const val scaleGap = 0.05f

        //图片的偏移量
        val transitionY = UiUtils.dp2px(7f).toFloat()
    }
}