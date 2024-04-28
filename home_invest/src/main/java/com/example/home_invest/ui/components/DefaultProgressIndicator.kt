package com.example.home_invest.ui.components

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.home_invest.R
import com.example.home_invest.databinding.DefaultProgressIndicatorBinding

class DefaultProgressIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var progress = 0
    private var itemView: DefaultProgressIndicatorBinding? = null


    init {
        itemView =
            DefaultProgressIndicatorBinding.inflate(LayoutInflater.from(context), this, true)

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.DefaultProgressIndicator, 0, 0)

            progress = typedArray.getInt(R.styleable.DefaultProgressIndicator_etProgress, 0)

            typedArray.recycle()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itemView?.progressBar?.setProgress(progress, true)
        } else {
            itemView?.progressBar?.progress = progress
        }
    }

    fun setProgress(progress: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itemView?.progressBar?.setProgress(progress, true)
        } else {
            itemView?.progressBar?.progress = progress
        }
    }
}