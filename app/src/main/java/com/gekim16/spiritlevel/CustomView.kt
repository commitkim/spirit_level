package com.gekim16.spiritlevel

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

private const val RATIO = .35f
private const val MARGIN = 20f

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr){

    private val circles = arrayListOf<Circle>()
    private val bitmaps = arrayListOf<Bitmap>()
    private val paint = Paint()

    private val levelCirclePaint = Paint()


    init {
        levelCirclePaint.strokeWidth = 3f
        levelCirclePaint.color = Color.BLACK
        levelCirclePaint.style = Paint.Style.STROKE

        initBitmaps()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        initCircles()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackground(canvas)

        circles.forEach {
            canvas?.drawBitmap(
                it.bitmap,
                it.x,
                it.y,
                levelCirclePaint
            )
        }
    }

    private fun initBitmaps(){
        bitmaps.clear()

        bitmaps.add(resizeBitmap(getBitmap(R.drawable.center), RATIO))
        bitmaps.add(resizeBitmap(getBitmap(R.drawable.up), RATIO))
        bitmaps.add(resizeBitmap(getBitmap(R.drawable.side), RATIO))
    }

    private fun initCircles(){
        circles.clear()

        val circleBitmap = resizeBitmap(getBitmap(R.drawable.circle), RATIO)
        val left = width / 3.toFloat()
        val top = height / 3.toFloat()

        bitmaps.forEach {
            circles.add(Circle(baseBitmap = it, bitmap = circleBitmap))
        }

        circles[0].setBase(left, top)
        circles[1].setBase(left, MARGIN)
        circles[2].setBase(MARGIN, top)
    }


    private fun drawBackground(canvas: Canvas?) {
        val left = width / 3.toFloat()
        val top = height / 3.toFloat()

        canvas?.drawBitmap(bitmaps[0], left, top, paint)
        canvas?.drawBitmap(bitmaps[1], left, MARGIN, paint)
        canvas?.drawBitmap(bitmaps[2], MARGIN, top, paint)
    }

    private fun resizeBitmap(bitmap: Bitmap, ratio: Float) = Bitmap.createScaledBitmap(
        bitmap,
        (bitmap.width * ratio).toInt(),
        (bitmap.height * ratio).toInt(),
        false
    )

    private fun getBitmap(resource: Int) = BitmapFactory.decodeResource(context.resources, resource)

    fun setCirclePoint(x: Float, y: Float){
        if(circles.size < 3) return

        circles[0].setPoint(x, y)
        circles[1].setPoint(x, 0f)
        circles[2].setPoint(0f, y)

        invalidate()
    }
}

data class Circle(
    val baseBitmap: Bitmap,
    val bitmap: Bitmap,
    var baseX: Float = 0f,
    var baseY: Float = 0f,
    var x: Float = 0f,
    var y: Float = 0f
) {

    fun setPoint(x: Float, y: Float) {
        this.x = baseX + (x * baseBitmap.width / 9.8 / 2).toFloat()
        this.y = baseY - (y * baseBitmap.height / 9.8 / 2).toFloat()
    }

    fun setBase(left: Float, top: Float) {
        this.baseX = left + (this.baseBitmap.width / 2) - (this.bitmap.width / 2)
        this.baseY = top + (this.baseBitmap.height / 2) - (this.bitmap.height / 2)
    }
}