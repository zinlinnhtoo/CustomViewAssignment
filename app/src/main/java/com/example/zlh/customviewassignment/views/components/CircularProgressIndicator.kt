package com.example.zlh.customviewassignment.views.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.example.zlh.customviewassignment.R

class CircularProgressIndicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var size = 0
    var paint = Paint()
    var oval = RectF()
    var borderWidth = 5f
    var progress = 0
    var textColor = Color.parseColor("#f26950")
    var progressBarColor = Color.parseColor("#f5c7bf")
    private var progressColor = Color.parseColor("#f26950")

    init {
        setUpAttributes(attrs)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val leftProgress = 100f - progress
        paint.apply {
            color = progressColor
            style = Paint.Style.STROKE
            textSize = size * 0.32f
            strokeWidth = borderWidth
            strokeCap = Paint.Cap.BUTT
        }
        oval.set(
            borderWidth / 2f,
            borderWidth / 2f,
            size - borderWidth / 2f,
            size - borderWidth / 2f
        )
        canvas?.drawArc(oval, -90f, progress * 3.6f, false, paint)

        paint.color = progressBarColor
        canvas?.drawArc(oval, -90f, -(leftProgress * 3.6f), false, paint)

        paint.style = Paint.Style.FILL
        paint.color = textColor
        canvas?.drawText("$progress%", size * 0.23f, size * 0.64f, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        size = w.coerceAtMost(h)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        context?.withStyledAttributes(attrs, R.styleable.CircularProgressIndicator) {
            progress = getInt(R.styleable.CircularProgressIndicator_progress, 0)
            borderWidth = getDimension(R.styleable.CircularProgressIndicator_progressBarRadius,borderWidth)
        }
    }
    companion object {
//        private const val DEFAULT_PROGRESS_BORDER = 5F
//        private const val DEFAULT_TEXT_COLOR =
//        private const val DEFAULT_PROGRESS_BAR_COLOR = Color.RED
//        private const val DEFAULT_PROGRESS_COLOR = Color.RED
    }
}