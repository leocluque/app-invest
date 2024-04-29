package com.example.home_invest.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CustomTabIndicatorView(context: Context) : View(context) {

    val paint = Paint()

    init {
        paint.color = Color.BLACK // Set indicator color
        paint.strokeWidth = 2f // Set indicator width
    }

    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(0f, 0f, width.toFloat(), 0f, paint)
    }
}
