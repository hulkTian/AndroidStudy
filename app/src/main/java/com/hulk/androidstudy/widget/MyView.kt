package com.hulk.androidstudy.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt

class MyView : View {
    private var paint: Paint = Paint()

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    /**
     * 颜色填充
     * @param color 16进制颜色值
     */
    private fun drawColor(@ColorInt color: Int, canvas: Canvas?) {
        canvas?.drawColor(Color.BLACK)
    }

    /**
     * 颜色填充
     */
    private fun drawARGB(a: Int, r: Int, g: Int, b: Int, canvas: Canvas?) {
        canvas?.drawARGB(a, r, g, b)
    }

    /**
     * 画圆
     * @param x 圆心X轴坐标
     * @param y 圆心Y轴坐标
     */
    private fun drawCircle(x: Float, y: Float, r: Float, canvas: Canvas?) {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        canvas?.drawCircle(x, y, r, paint)
    }

    /**
     * 画椭圆
     * left、top、right、bottom，椭圆四个边界点的坐标
     */
    private fun drawOval(left: Float, top: Float, right: Float, bottom: Float, canvas: Canvas?) {
        //空心椭圆
        paint.style = Paint.Style.STROKE
        canvas?.drawOval(left, top, right, bottom, paint)
        //实心椭圆
        paint.style = Paint.Style.FILL
        canvas?.drawOval(left, top, right, bottom, paint)
        //重载方法
        val rect = RectF(left, top, right, bottom)
        canvas?.drawOval(rect, paint)
    }

    /**
     * 画线
     */
    private fun drawLine(
        startX: Float,
        startY: Float,
        stopX: Float,
        stopY: Float,
        canvas: Canvas?
    ) {
        //直线不是封闭图形，style对直线没有影响
        canvas?.drawLine(startX, startY, stopX, stopY, paint)
        //批量画线
        canvas?.drawLines(floatArrayOf(startX * 2, startY * 2, stopX * 2, stopY * 2), paint)
        //控制批量画线数量和开始坐标
        canvas?.drawLines(
            floatArrayOf(
                startX * 2, startY * 2, stopX * 2, stopY * 2,
                startX * 3, startY * 3, stopX * 3, stopY * 3,
                startX * 4, startY * 4, stopX * 4, stopY * 4,
                startX * 5, startY * 5, stopX * 5, stopY * 5
            ), 4, 3, paint
        )
    }

    /**
     * 画圆角矩形
     * left、top、right、bottom四个角的坐标
     * rx、ry圆角的横向半径和纵向半径
     */
    private fun drawRoundRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        rx: Float,
        ry: Float,
        canvas: Canvas?
    ) {
        canvas?.drawRoundRect(left, top, right, bottom, rx, ry, paint)
    }

    /**
     * 绘制弧形或扇形
     * left、top、right、bottom椭圆的四个边界点坐标
     * startAngle、sweepAngle 起始度数和弧形的夹角度数,X轴正方向角度为0，顺势正方向为正，逆时针为负
     * useCenter 是否连线到中心点
     */
    private fun drawArc(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        startAngle: Float,
        sweepAngle: Float,
        canvas: Canvas?
    ) {
        paint.style = Paint.Style.FILL
        //绘制扇形
        canvas?.drawArc(left, top, right, bottom, startAngle, sweepAngle, true, paint)
        //绘制弧形
        canvas?.drawArc(left, top, right, bottom, startAngle, sweepAngle, false, paint)
        paint.style = Paint.Style.STROKE
        //绘制不封口的弧形
        canvas?.drawArc(left, top, right, bottom, startAngle, sweepAngle, false, paint)
    }

    /**
     * 绘制路径
     */
    private fun drawPath(path: Path) {
        //添加扇形
        path.addArc(200f, 200f, 400f, 400f, -225f, 225f)
        //根据上个路径继续添加扇形
        path.arcTo(
            400f, 200f, 600f, 400f, -180f,
            225f, false
        )
        //画线
        path.lineTo(400f, 542f)
    }

    /**
     * Path Api
     * 第一组：addXxx()-添加子图形
     * 添加圆形
     * x,y,radius圆的基本信息
     * dir圆路径的方向：顺时针（CW），逆时针（CCW）,用于图形相交部分判断填充范围
     */
    private fun addCircle(path: Path, x: Float, y: Float, radius: Float, dir: Path.Direction) {
        path.addCircle(x, y, radius, dir)
    }
}