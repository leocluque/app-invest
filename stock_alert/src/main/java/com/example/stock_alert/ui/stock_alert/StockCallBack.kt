package com.example.stock_alert.ui.stock_alert

import androidx.recyclerview.widget.DiffUtil
import com.example.network.data.response.ProductsResponse

class StockCallBack(
    private val oldList: List<ProductsResponse>,
    private val newList: List<ProductsResponse>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
