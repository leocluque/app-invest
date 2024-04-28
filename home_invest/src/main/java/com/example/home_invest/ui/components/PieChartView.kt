package com.example.home_invest.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class PieChartView : View {
    private val dataValues: MutableList<Float> = ArrayList()
    private val colors: MutableList<Int> = ArrayList()
    private val paint = Paint()

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        // Adicione dados de exemplo
        dataValues.add(30f)
        dataValues.add(20f)
        dataValues.add(50f)

        // Adicione cores de exemplo
        colors.add(Color.BLUE)
        colors.add(Color.RED)
        colors.add(Color.GREEN)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = Math.min(centerX, centerY)
        val rectF = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        var startAngle = 0f
        for (i in dataValues.indices) {
            val sweepAngle = 360 * (dataValues[i] / sum(dataValues))
            paint.color = colors[i]
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
            startAngle += sweepAngle
        }
    }

    private fun sum(values: List<Float>): Float {
        var sum = 0f
        for (value in values) {
            sum += value
        }
        return sum
    }
}

