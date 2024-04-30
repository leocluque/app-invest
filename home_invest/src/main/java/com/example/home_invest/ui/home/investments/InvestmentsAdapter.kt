package com.example.home_invest.ui.home.investments

import ShapeUtils.setShapeColor
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home_invest.databinding.ItemInvestmentsBinding
import com.example.home_invest.ui.extensions.formatCurrencyBRL
import com.example.network.data.response.ContractedProducts

class InvestmentsAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = listOf<ContractedProducts>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                InvestmentsCallBack(
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
            ItemInvestmentsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    inner class ItemViewHolder(private val binding: ItemInvestmentsBinding) :
        RecyclerView.ViewHolder(binding.root.rootView) {
        fun bind(item: ContractedProducts) {
            binding.apply {
                val percentage = item.percentage.toInt().toString()
                percentageTv.text = "$percentage %"
                nameInvestTv.text = item.productName
                balanceValueTv.text = item.totalInvested.toFloat().formatCurrencyBRL()
                setShapeColor(bgColorIv, item.color)
            }
        }
    }

}