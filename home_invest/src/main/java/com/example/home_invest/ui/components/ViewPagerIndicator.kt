package com.example.home_invest.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.home_invest.R

class ViewPagerIndicator(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var currentPage = 0
    private var totalPages = 0
    private val backgroundPaint = Paint()
    private val indicatorPaint = Paint()
    private val cornerRadius = 1000f // Valor fixo para o raio das bordas

    init {
        backgroundPaint.isAntiAlias = true
        backgroundPaint.style = Paint.Style.FILL

        indicatorPaint.isAntiAlias = true
        indicatorPaint.style = Paint.Style.FILL

        // Set default background and indicator colors
        backgroundPaint.color = ContextCompat.getColor(context, R.color.background_indicator)
        indicatorPaint.color = ContextCompat.getColor(context, R.color.indicator_progress)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        // Draw background with rounded corners
        val rect = RectF(0f, 0f, width, height)
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, backgroundPaint)

        // Calculate spacing and indicator size based on available space
        val spacing = width * 0.1f
        val indicatorSize = (width - (totalPages - 1) * spacing) / totalPages

        // Draw indicators
        for (i in 0 until totalPages) {
            val x = i * (indicatorSize + spacing)
            val y = height / 2f
            val radius = indicatorSize / 2f

            // Check if the current indicator is the progress indicator
            val isProgressIndicator = i == currentPage

            // Set the indicator color based on whether it's the progress indicator or not
            indicatorPaint.color = if (isProgressIndicator) indicatorColor else indicatorDefaultColor

            // Draw the indicator circle
            canvas.drawCircle(x + radius, y, radius, indicatorPaint)
        }
    }

    fun setCurrentPage(page: Int) {
        currentPage = page
        invalidate() // Trigger redraw when page changes
    }

    fun setTotalPages(pages: Int) {
        totalPages = pages
        invalidate() // Trigger redraw when total pages change
    }

    fun setNewBackgroundColor(color: Int) {
        backgroundPaint.color = color
        invalidate() // Trigger redraw when background color changes
    }

    fun setIndicatorColor(color: Int) {
        indicatorColor = color
        invalidate() // Trigger redraw when indicator color changes
    }

    // Optionally, define default and selected indicator colors
    private var indicatorDefaultColor = ContextCompat.getColor(context, R.color.background_indicator)
    private var indicatorColor = ContextCompat.getColor(context, R.color.indicator_progress)
}