package com.example.home_invest.ui.home.investments

import androidx.recyclerview.widget.DiffUtil
import com.example.network.data.response.ContractedProducts

class InvestmentsCallBack(
    private val oldList: List<ContractedProducts>,
    private val newList: List<ContractedProducts>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].productName == newList[newItemPosition].productName

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].productName == newList[newItemPosition].productName
}
