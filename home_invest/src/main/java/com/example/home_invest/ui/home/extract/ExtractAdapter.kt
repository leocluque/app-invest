package com.example.home_invest.ui.home.extract

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home_invest.databinding.ItemExtractBinding
import com.example.home_invest.databinding.ItemInvestmentsBinding

class ExtractAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = listOf<String>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ExtractCallBack(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            ItemExtractBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    inner class ItemViewHolder(private val binding: ItemExtractBinding) :
        RecyclerView.ViewHolder(binding.root.rootView) {
        fun bind(item: String) {
            binding.apply {

            }
        }
    }

}