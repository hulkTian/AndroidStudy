package com.hulk.androidstudy.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

class MyTextView : AppCompatTextView {
    private var paint: Paint = Paint()
    private val text = "MyTextView"

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        paint.isAntiAlias = true
        paint.textSize = 40f
        paint.color = Color.RED
        paint.strokeWidth = 2f
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        //水平中线
        canvas.drawLine(0f, height / 2f, width.toFloat(), height / 2f, paint)
        //垂直中线
        canvas.drawLine(width / 2f, 0f, width / 2f, height.toFloat(), paint)
        //baseline
        paint.color = Color.BLACK
//        val baseline = height / 2f + (paint.fontMetrics.descent - paint.fontMetrics.ascent) / 2f - paint.fontMetrics.descent
        val baseline = height / 2f - (paint.fontMetrics.ascent + paint.fontMetrics.descent) / 2f
        canvas.drawLine(0f, baseline, width.toFloat(), baseline, paint)
        //top
        Log.i("top","top=" + paint.fontMetrics.top)
        paint.color = Color.GREEN
        val top: Float = baseline + paint.fontMetrics.top
        canvas.drawLine(0f, top, width.toFloat(), top, paint)
        //bottom
        Log.i("bottom","bottom=" + paint.fontMetrics.bottom)
        paint.color = Color.GREEN
        val bottom: Float = baseline + paint.fontMetrics.bottom
        canvas.drawLine(0f, bottom, width.toFloat(), bottom, paint)
        //descent
        Log.i("descent","descent=" + paint.fontMetrics.descent)
        paint.color = Color.BLUE
        val descent: Float = baseline + paint.fontMetrics.descent
        canvas.drawLine(0f, descent, width.toFloat(), descent, paint)
        //ascent
        Log.i("ascent","ascent=" + paint.fontMetrics.ascent)
        paint.color = Color.BLUE
        val ascent: Float = baseline + paint.fontMetrics.ascent
        canvas.drawLine(0f, ascent, width.toFloat(), ascent, paint)
        //文字
        val textWidth = paint.measureText(text)
        val x = width / 2 - textWidth / 2
        canvas.drawText("MyTextView", x, baseline, paint)
    }
}