package com.example.home_invest.ui.home.extract

import androidx.recyclerview.widget.DiffUtil
import com.example.network.data.response.ExtractResponse

class ExtractCallBack(
    private val oldList: List<ExtractResponse>,
    private val newList: List<ExtractResponse>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
