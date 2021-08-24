package com.hulk.androidstudy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.hulk.common.utils.UiUtils
import kotlin.math.max

class FollowLayout : ViewGroup {
    private val mHorizontalSpacing = UiUtils.dp2px(16f) //每个item横向间距
    private val mVerticalSpacing = UiUtils.dp2px(8f) //每个item横向间距
    private val allLines: ArrayList<List<View>> = ArrayList() // 记录所有的行，一行一行的存储，用于layout
    var lineHeights: ArrayList<Int> = ArrayList() // 记录每一行的行高，用于layout

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int)
            : super(context, attrs, defStyle)

    //不滚动时返回false
    override fun shouldDelayChildPressedState() = false

    /**
     * 度量整个View树
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //多次调用onMeasure时需要重写保存结果
        allLines.clear()
        lineHeights.clear()
        //1.获取所有子节点的数量
        val count = childCount
        var lineWidthUsed = 0 //记录这行已经使用了多宽的size
        var lineHeight = 0 //记录当前行的最大高度
        //获取当前View的父View给的测量宽度值和测量高度值
        val selfWidth = MeasureSpec.getSize(widthMeasureSpec)
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec)
        var lineViews = ArrayList<View>() //保存一行中的所有的view
        var parentNeededHeight = 0//measure过程中，子View要求的父ViewGroup的高
        var parentNeededWidth = 0//measure过程中，子View要求的父ViewGroup的宽

        //1.2. 遍历子节点
        for (i in 0 until count) {
            //2. 获取子节点
            val child = getChildAt(i)
            //没有隐藏才需要度量
            if (child.visibility != GONE) {
                //3.1. 度量子节点，结果会在getMeasuredWidth/getMeasuredHeight中返回
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
                //3.2. 获取子view的度量宽高
                val childMeasuredWidth: Int = child.measuredWidth
                val childMeasuredHeight: Int = child.measuredHeight
                //4.0 判断是否需要换行，当前View宽度+累计宽度+View的水平间距 > 当前布局的宽度时需要换行
                if (childMeasuredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {
                    //保存当前行的所有子View
                    allLines.add(lineViews)
                    //保存当前行高
                    lineHeights.add(lineHeight)
                    //记录当前布局需要的最大宽度和累计高度
                    parentNeededHeight += lineHeight + mVerticalSpacing
                    parentNeededWidth = max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing)
                    //准备开始记录下一行
                    lineViews = ArrayList()
                    lineWidthUsed = 0
                    lineHeight = 0
                }
                //4.1 记录每行需要展示的View
                lineViews.add(child)
                //4.2 记录当前行的最大高度
                lineHeight = max(childMeasuredHeight, lineHeight)
                //4.3 每行都会有自己的宽和高
                lineWidthUsed += childMeasuredWidth + mHorizontalSpacing

                //4.4处理最后一行数据
                if (i == count - 1) {
                    allLines.add(lineViews)
                    lineHeights.add(lineHeight)
                    parentNeededHeight += lineHeight + mVerticalSpacing
                    parentNeededWidth =
                        parentNeededWidth.coerceAtLeast(lineWidthUsed + mHorizontalSpacing)
                }
            }
        }
        //5. 提交最后度量的结果
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val realWidth = if (widthMode == MeasureSpec.EXACTLY) selfWidth else parentNeededWidth
        val realHeight = if (heightMode == MeasureSpec.EXACTLY) selfHeight else parentNeededHeight
        setMeasuredDimension(realWidth, realHeight)
    }

    /**
     * 开始确定每个View的位置
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val lineCount = allLines.size

        var curL = paddingLeft
        var curT = paddingTop

        for (i in 0 until lineCount) {
            val lineViews = allLines[i]
            val lineHeight = lineHeights[i]
            for (j in lineViews.indices) {
                val view = lineViews[j]
                val left = curL
                val top = curT
                val right = left + view.measuredWidth
                val bottom = top + view.measuredHeight
                view.layout(left, top, right, bottom)
                curL = right + mHorizontalSpacing
            }
            curT += lineHeight + mVerticalSpacing
            curL = paddingLeft
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): CustomLayout.LayoutParams? {
        return CustomLayout.LayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): CustomLayout.LayoutParams? {
        return CustomLayout.LayoutParams(
            CustomLayout.LayoutParams.MATCH_PARENT,
            CustomLayout.LayoutParams.MATCH_PARENT
        )
    }

    override fun generateLayoutParams(
        p: ViewGroup.LayoutParams?
    ): ViewGroup.LayoutParams? {
        return LayoutParams(p)
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean {
        return p is CustomLayout.LayoutParams
    }

    /**
     * 自定义子布局信息
     */
    class LayoutParams : MarginLayoutParams {
        constructor(c: Context, attrs: AttributeSet?) : super(c, attrs)
        constructor(width: Int, height: Int) : super(width, height)
        constructor(source: ViewGroup.LayoutParams?) : super(source)
    }
}