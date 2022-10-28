package com.example.zlh.customviewassignment.views.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import androidx.core.view.ContentInfoCompat.Flags
import com.example.zlh.customviewassignment.R
import kotlin.math.min

open class CircularAvatarImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

//    private val path = Path()
    private val mBitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBitmapDrawBounds = RectF()
    private var mStrokeBounds = RectF()
    private val mShaderMatrix = Matrix()
    private var mInitialized: Boolean = false
    private var mBitmap : Bitmap? = null
    private var mBitmapShader: Shader? = null


    private var mStrokeColor = DEFAULT_STROKE_COLOR
    private var mStrokeWidth = DEFAULT_STROKE_WIDTH

    init {
        setUpAttributes(attrs)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {

//        var x = width / 2f
//        var y = height / 2f
//        var radius = x
//
//
//        path.addCircle( width / 2f, height / 2f, width / 2f, Path.Direction.CCW)
//
//        canvas?.clipPath(path)
//
//        paint.color = Color.RED
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 4.0f
//        canvas?.drawCircle(x, y, radius, paint)
        drawBitmap(canvas)
        drawStroke(canvas)
        super.onDraw(canvas)
    }

    private fun drawBitmap(canvas: Canvas?) {
        canvas?.drawOval(mBitmapDrawBounds, mBitmapPaint)
    }

    private fun drawStroke(canvas: Canvas?) {
        if (mStrokePaint.strokeWidth > 0f) {
            canvas?.drawOval(mStrokeBounds, mStrokePaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        updateCircleDrawBounds(mBitmapDrawBounds)
        super.onSizeChanged(w, h, oldw, oldh)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.CircleImageView) {
            mStrokeColor = getColor(R.styleable.CircleImageView_strokeColor, DEFAULT_STROKE_COLOR)
            mStrokeWidth = getDimension(R.styleable.CircleImageView_strokeWidth, DEFAULT_STROKE_WIDTH)
        }

        mStrokePaint.apply {
            color = mStrokeColor
            style = Paint.Style.STROKE
            strokeWidth = mStrokeWidth
        }

        mInitialized = true
        setUpBitmap()
    }

    private fun setUpBitmap() {
        if (!mInitialized) {
            return
        }
        mBitmap = getBitmapFromDrawable(drawable)
        if (mBitmap == null) {
            return
        }
        mBitmapShader = BitmapShader(mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mBitmapPaint.shader = mBitmapShader
        updateBitmapSize()
    }

    private fun updateBitmapSize() {
        if (mBitmap == null) return
        val dx: Float
        val dy: Float
        val scale: Float

        if (mBitmap!!.width < mBitmap!!.height) {
            scale = mBitmapDrawBounds.width() / mBitmap!!.width.toFloat()
            dx = mBitmapDrawBounds.left
            dy = mBitmapDrawBounds.top - mBitmap!!.height * scale / 2f + mBitmapDrawBounds.width() / 2f
        } else {
            scale = mBitmapDrawBounds.height() / mBitmap!!.height.toFloat()
            dx = mBitmapDrawBounds.left - mBitmap!!.width * scale / 2f + mBitmapDrawBounds.width() / 2f
            dy = mBitmapDrawBounds.top
        }
        mShaderMatrix.setScale(scale, scale)
        mShaderMatrix.postTranslate(dx, dy)
        mBitmapShader!!.setLocalMatrix(mShaderMatrix)
    }

    private fun getBitmapFromDrawable(drawable: Drawable?) : Bitmap? {

        if (drawable != null) {
            if (drawable is BitmapDrawable) { return drawable.bitmap }
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        } else {
            return null
        }

    }

    private fun updateCircleDrawBounds(bounds: RectF) {
        val contentWidth = width - paddingLeft - paddingRight.toFloat()
        val contentHeight = height - paddingTop - paddingBottom.toFloat()
        var left = paddingLeft.toFloat()
        var top = paddingTop.toFloat()
        if (contentWidth > contentHeight) {
            left += (contentWidth - contentHeight) / 2f
        } else {
            top += (contentHeight - contentWidth) / 2f
        }
        val diameter = min(contentWidth, contentHeight)
        bounds[left, top, left + diameter] = top + diameter
    }

    companion object {
        private const val DEFAULT_STROKE_COLOR =  Color.TRANSPARENT
        private const val DEFAULT_STROKE_WIDTH =  4.0f
    }
}