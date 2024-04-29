package com.example.home_invest.ui.components

import android.animation.ValueAnimator
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

    private var currentRotationAngle = 0f

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 60f

        // Inicia a animação
        startAnimation()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = minOf(centerX, centerY) - paint.strokeWidth / 2f

        var startAngle = -90f + currentRotationAngle // Start angle at -90 degrees (top of circle)

        for ((index, item) in progressItems.withIndex()) {
            val sweepAngle = item.percentage / 100f * 360f
            val rectF =
                RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

            paint.color = Color.parseColor(item.color)
            canvas.drawArc(rectF, startAngle, sweepAngle, false, paint)

            // Adiciona espaço após cada item, exceto para o último item
            if (index < progressItems.size - 1) {
                startAngle += sweepAngle + segmentSpacing // Add spacing after each segment
            }
        }
    }

    private fun startAnimation() {
        // Define o valor inicial e final da animação
        val startAngle = 0f
        val endAngle = 360f

        // Cria o animador de valores
        val animator = ValueAnimator.ofFloat(startAngle, endAngle)
        animator.duration = 2000 // Define a duração da animação em milissegundos
        animator.addUpdateListener { animation ->
            // Atualiza o ângulo de rotação atual
            currentRotationAngle = animation.animatedValue as Float
            // Solicita uma nova renderização da View
            invalidate()
        }
        animator.start() // Inicia a animação
    }

    fun setItems(list: List<ProgressItem>) {
        progressItems.clear() // Limpa os itens antigos
        progressItems.addAll(list)
        invalidate() // Notifica a View para redesenhar
    }
}

data class ProgressItem(val productName: String, val percentage: Float, val color: String)



