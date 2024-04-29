package com.example.home_invest.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.home_invest.databinding.CustomCircleProgressLayoutBinding

class CircleProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var itemView: CustomCircleProgressLayoutBinding? = null

    init {
        itemView =
            CustomCircleProgressLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun configureComponent(
        title: String? = null,
        subtitle: String? = null,
        value: String? = null,
        list: List<ProgressItem> = listOf()
    ) {
        title?.let {
            itemView?.totalProductsTv?.text = it
        }

        subtitle?.let {
            itemView?.totalProductsTitleTv?.text = it
        }

        value?.let {
            itemView?.balanceTv?.text = it
        }
        itemView?.circularProgressBar?.setItems(list)
    }
    fun getTitle(): String {
        return itemView?.totalProductsTv?.text.toString()
    }

    fun getSubtitle() : String {
        return itemView?.totalProductsTitleTv?.text.toString()
    }

    fun getBalance() : String {
        return itemView?.balanceTv?.text.toString()
    }

    fun getItems(): List<ProgressItem> {
        return itemView?.circularProgressBar?.getItems()?.toList() ?: emptyList()
    }
}