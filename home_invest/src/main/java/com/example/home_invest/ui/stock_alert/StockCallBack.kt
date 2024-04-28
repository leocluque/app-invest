package com.example.home_invest.ui.stock_alert

import androidx.recyclerview.widget.DiffUtil

class StockCallBack(
    private val oldList: List<String>,
    private val newList: List<String>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
