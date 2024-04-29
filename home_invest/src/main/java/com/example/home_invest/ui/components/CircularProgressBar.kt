package com.example.home_invest.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CircularProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint()
    private val progressItems = mutableListOf<ProgressItem>()
    private val segmentSpacing = 1f // Adjust spacing as needed

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 60f
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = minOf(centerX, centerY) - paint.strokeWidth / 2f

        var startAngle = -90f // Start angle at -90 degrees (top of circle)

        for (item in progressItems) {
            val sweepAngle = item.percentage / 100f * 360f
            val rectF =
                RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

            paint.color = Color.parseColor(item.color)
            canvas.drawArc(rectF, startAngle, sweepAngle, false, paint)

            startAngle += sweepAngle + segmentSpacing // Add spacing after each segment
        }
    }

    fun setItems(list: List<ProgressItem>) {
        progressItems.clear() // Limpa os itens antigos
        progressItems.addAll(list)
        invalidate() // Notifica a View para redesenhar
    }
}

data class ProgressItem(val productName: String, val percentage: Float, val color: String)

