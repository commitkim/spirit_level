package com.gekim16.spiritlevel

import android.content.Context
import android.util.AttributeSet

class VerticalLevel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Level(context, attrs, defStyleAttr) {

    init {
        setBackgroundResource(R.drawable.level_vertical)
    }

    override fun setCirclePoint(x: Double, y: Double) {
        circle.setPoint(0.0, y)

        invalidate()
    }
}