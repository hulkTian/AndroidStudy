package com.hulk.androidstudy.widget

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import com.hulk.androidstudy.R
import com.hulk.common.utils.UiUtils

/**
 * 点赞效果
 * Created by tzh on 2021/3/24.
 */
class GiveLikeView : View {
    private var paint: Paint = Paint()
    private var likeNumber = 119
    private var isLiked = false
    private var selectedBitmap: Bitmap
    private var unSelectedBitmap: Bitmap
    private var selectedShining: Bitmap
    private var baseline: Float = 0f
    private var baselineOfUp: Float = 0f
    private var baselineOfDown: Float = 0f
    private var currentBaselineOfUp: Float = 0f
    private var currentBaselineOfDown: Float = 0f
    private var scale = 1f
    private var radius = 0f
    private var radiusAlpha = 255
    private var textAlphaOfDown = 0
    private var textAlphaOfUp = 255
    private var textPartLeft: String
    private var textPartRightUp: String = ""
    private var textPartRightDown: String = ""
    private val maxRadius: Float
    private val minRadius: Float
    private lateinit var animatorForScale: ValueAnimator
    private lateinit var animatorForRadius: ValueAnimator
    private lateinit var animatorForAlpha: ValueAnimator
    private lateinit var animatorForTextAdd: ValueAnimator
    private lateinit var animatorForTextSub: ValueAnimator
    private lateinit var animatorSet: AnimatorSet

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        textPartLeft = likeNumber.toString()
        paint.color = Color.GRAY
        paint.textSize = UiUtils.dp2px(14f).toFloat()
        selectedBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected)
        selectedShining = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected_shining)
        unSelectedBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_unselected)
        maxRadius = selectedBitmap.width / 2 * 1.2f
        minRadius = selectedBitmap.width / 2 * 0.9f
        //点击监听点赞和取消点赞
        this.setOnClickListener {
            initAnim()
            isLiked = !isLiked
            textPartLeft = ""
            textPartRightUp = ""
            textPartRightDown = ""
            val tempLikeNumber = if (isLiked) likeNumber + 1 else likeNumber - 1
            val tempLikeNumberStr = tempLikeNumber.toString().reversed()
            val likeNumberStr = likeNumber.toString().reversed()
            //长度不同，全部位数变化
            if (tempLikeNumberStr.length != likeNumberStr.length) {
                textPartRightUp = likeNumberStr
                textPartRightDown = tempLikeNumberStr
            } else {//长度相同，部分位数变化
                for (i in tempLikeNumberStr.indices) {
                    if (tempLikeNumberStr[i] == likeNumberStr[i]) {//相同位字符相同，绘制时不走动画
                        textPartLeft = likeNumberStr[i].plus(textPartLeft)
                    } else {//相同位字符不同，点赞时：原字符在上；取消点赞时：原字符在下
                        if (isLiked) {
                            textPartRightUp = likeNumberStr[i].plus(textPartRightUp)
                            textPartRightDown = tempLikeNumberStr[i].plus(textPartRightDown)
                        } else {
                            textPartRightUp = tempLikeNumberStr[i].plus(textPartRightUp)
                            textPartRightDown = likeNumberStr[i].plus(textPartRightDown)
                        }
                    }
                }
            }
            likeNumber = tempLikeNumber
            animatorSet.start()
        }
    }

    private fun initAnim() {
        if (this::animatorForScale.isInitialized) return
        animatorForScale = ValueAnimator.ofFloat(0.8f, 1f)
        animatorForScale.duration = 400
        animatorForScale.interpolator = BounceInterpolator()
        animatorForScale.addUpdateListener {
            scale = it.animatedValue as Float
            invalidate()
        }

        animatorForRadius = ValueAnimator.ofFloat(minRadius, maxRadius)
        animatorForRadius.duration = 100
        animatorForRadius.interpolator = AccelerateInterpolator()
        animatorForRadius.addUpdateListener {
            radius = it.animatedValue as Float
        }
        animatorForAlpha = ValueAnimator.ofInt(255, 0)
        animatorForAlpha.duration = 100
        animatorForAlpha.interpolator = AccelerateInterpolator()
        animatorForAlpha.addUpdateListener {
            radiusAlpha = it.animatedValue as Int
        }
        animatorForTextAdd = ValueAnimator.ofFloat(baseline, baselineOfUp)
        animatorForTextAdd.duration = 400
        animatorForTextAdd.addUpdateListener {
            if (isLiked) {
                currentBaselineOfUp = it.animatedValue as Float
                currentBaselineOfDown = (1 - it.animatedFraction) * baselineOfDown
                textAlphaOfDown = (it.animatedFraction * 255).toInt()
                textAlphaOfUp = ((1 - it.animatedFraction) * 255).toInt()
            }
        }
        animatorForTextSub = ValueAnimator.ofFloat(baseline, baselineOfDown)
        animatorForTextSub.duration = 400
        animatorForTextSub.addUpdateListener {
            if (!isLiked) {
                currentBaselineOfDown = it.animatedValue as Float
                currentBaselineOfUp = (1 - it.animatedFraction) * baselineOfUp
                textAlphaOfUp = (it.animatedFraction * 255).toInt()
                textAlphaOfDown = ((1 - it.animatedFraction) * 255).toInt()
            }
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animatorForScale, animatorForRadius, animatorForAlpha,
                animatorForTextAdd, animatorForTextSub)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        drawText(canvas)
        if (isLiked)
            giveLike(canvas)
        else
            canGiveLike(canvas)
    }

    private fun giveLike(canvas: Canvas?) {
        canvas?.save()
        paint.alpha = 255
        canvas?.scale(scale, scale, 50f + selectedBitmap.width / 2f,
                getBitmapTop(selectedBitmap) + selectedBitmap.height / 2f)
        canvas?.drawBitmap(selectedBitmap, 50f, getBitmapTop(selectedBitmap), paint)
        canvas?.restore()
        if (radius < maxRadius) {
            paint.strokeWidth = UiUtils.dp2px(2f).toFloat()
            paint.style = Paint.Style.STROKE
            paint.color = Color.argb(radiusAlpha, 227, 88, 64)
            canvas?.drawCircle(50f + selectedBitmap.width / 2f,
                    getBitmapTop(selectedBitmap) + selectedBitmap.height / 2f, radius, paint)
        }
        paint.alpha = 255
        if (scale >= 0.9f)
            canvas?.drawBitmap(selectedShining, 50f + (selectedBitmap.width - selectedShining.width) / 2f,
                    (height - selectedBitmap.height - selectedShining.height) / 2f, paint)
    }

    private fun canGiveLike(canvas: Canvas?) {
        canvas?.save()
        paint.alpha = 255
        canvas?.scale(scale, scale, 50f + unSelectedBitmap.width / 2f,
                getBitmapTop(unSelectedBitmap) + unSelectedBitmap.height / 2f)
        canvas?.drawBitmap(selectedBitmap, 50f, getBitmapTop(selectedBitmap), paint)
        canvas?.drawBitmap(unSelectedBitmap, 50f, getBitmapTop(unSelectedBitmap), paint)
        canvas?.restore()
    }

    private fun drawText(canvas: Canvas?) {
        paint.color = Color.GRAY
        paint.style = Paint.Style.FILL
        if (textPartLeft.isNotEmpty()) {
            paint.alpha = 255
            canvas?.drawText(textPartLeft, 50f + selectedBitmap.width, getBaseLine(), paint)
        }
        val startX = 50f + selectedBitmap.width + paint.measureText(textPartLeft)
        if (textPartRightUp.isNotEmpty()) {
            paint.alpha = textAlphaOfUp
            canvas?.drawText(textPartRightUp, startX, currentBaselineOfUp, paint)
        }
        if (textPartRightDown.isNotEmpty()) {
            paint.alpha = textAlphaOfDown
            canvas?.drawText(textPartRightDown, startX, currentBaselineOfDown, paint)
        }
    }

    private fun getBaseLine(): Float {
        if (baseline == 0f) {
            baseline = height / 2 + (paint.fontMetrics.descent - paint.fontMetrics.ascent) / 2 - paint.fontMetrics.descent
            baselineOfUp = baseline - paint.fontMetrics.descent + paint.fontMetrics.ascent
            baselineOfDown = baseline + paint.fontMetrics.descent - paint.fontMetrics.ascent
        }
        return baseline
    }

    private fun getBitmapTop(bitmap: Bitmap) = (height - bitmap.height) / 2f

}