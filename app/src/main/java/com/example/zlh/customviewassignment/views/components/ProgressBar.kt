package com.example.zlh.customviewassignment.views.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.example.zlh.customviewassignment.R

class ProgressBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var size = 0
    var paint = Paint()
    var path = Path()
    var oval = RectF()
    var progress = 0
    var borderWidth = 10f
    var progressColor = Color.parseColor("#2cc09c")
    var progressBarColor = Color.DKGRAY
    init {
        context?.withStyledAttributes(attrs, R.styleable.ProgressBar){
            progress = getInt(R.styleable.ProgressBar_progressBarProgress,0)
            borderWidth = getDimension(R.styleable.ProgressBar_progressBarHeight, borderWidth)
        }
    }
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        oval.set(borderWidth / 2, borderWidth / 2, size - borderWidth / 2f, size - borderWidth / 2)
        paint.apply {
            color = Color.parseColor("#2cc09c")
            style = Paint.Style.FILL
            strokeWidth = borderWidth
        }
        val rectangle = RectF(0f, 0f, size.toFloat(), borderWidth/2)
        path.addRoundRect(rectangle, 20f, 20f, Path.Direction.CCW)
        canvas?.clipPath(path)
        paint.color = progressBarColor
        canvas?.drawLine(size * 0.7f, 0f, size * 1f, 0f, paint)
        paint.color = progressColor
        val newRectangle = RectF(0f, 0f, size * 0.8f, borderWidth/2)
        canvas?.drawRoundRect(newRectangle, 20f, 20f, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        size = w
    }


}