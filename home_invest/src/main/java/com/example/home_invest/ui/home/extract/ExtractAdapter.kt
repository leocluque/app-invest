package com.example.home_invest.ui.home.extract

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home_invest.R
import com.example.home_invest.databinding.ItemExtractBinding
import com.example.home_invest.ui.extensions.dateFromString
import com.example.home_invest.ui.extensions.formatCurrencyBRL
import com.example.home_invest.ui.extensions.isSameDay
import com.example.home_invest.ui.extensions.setVisible
import com.example.network.data.response.ExtractResponse
import com.example.network.data.response.TransactionType

class ExtractAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = listOf<ExtractResponse>()
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
                holder.bind(list[position], if (position == 0) null else list[position - 1])            }
        }
    }

    inner class ItemViewHolder(private val binding: ItemExtractBinding) :
        RecyclerView.ViewHolder(binding.root.rootView) {
        fun bind(item: ExtractResponse, previousItem: ExtractResponse?) {
            binding.apply {
                transactionId.text = item.name
                valueTv.text = item.amount.toFloat().formatCurrencyBRL()
                if (item.type == TransactionType.EXPENSE) {
                    extractIndicatorIv.setImageResource(R.drawable.arrow_up_extract)
                } else {
                    extractIndicatorIv.setImageResource(R.drawable.arrow_down_extract)
                }

                val datePrevious = previousItem?.date?.dateFromString("dd/MM/yyyy")
                val date = item.date.dateFromString("dd/MM/yyyy")
                transactionDateLl.setVisible(datePrevious?.let { date?.isSameDay(it) } == true)

                dateTv.text = item.date
                balanceDayTv.text = item.balanceOfDay.toFloat().formatCurrencyBRL()
            }
        }
    }

}