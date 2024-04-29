package com.example.stock_alert.ui.stock_alert

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductStatus
import com.example.network.data.response.ProductsResponse
import com.example.stock_alert.R
import com.example.stock_alert.databinding.ItemStockBinding
import com.example.stock_alert.ui.extensions.formatCurrencyBRL

class StockAdapter(
    private val context: Context,
    private val onClickEdit: (CreateProductAlertRequest) -> Unit,
    private val onClickDelete: (CreateProductAlertRequest) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = listOf<ProductsResponse>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                StockCallBack(
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
            ItemStockBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    inner class ItemViewHolder(private val binding: ItemStockBinding) :
        RecyclerView.ViewHolder(binding.root.rootView) {
        fun bind(item: ProductsResponse) {
            binding.apply {
                nameInvestTv.text = item.productName
                when (item.status) {
                    ProductStatus.AVAILABLE -> {
                        stockTv.text = context.getString(R.string.stock_alert_available_product)
                        bgStatusIv.setImageResource(R.drawable.bg_stock_green)
                    }

                    ProductStatus.NOT_AVAILABLE -> {
                        stockTv.text = context.getString(R.string.stock_alert_not_available_product)
                        bgStatusIv.setImageResource(R.drawable.bg_stock_red)

                    }
                }
                valueProductTv.text = item.productValue.toFloat().formatCurrencyBRL()

                stockDeleteIv.setOnClickListener {
                    onClickDelete.invoke(
                        CreateProductAlertRequest(
                            id = item.id,
                            productName = item.productName,
                            status = item.status,
                            productValue = item.productValue
                        )
                    )
                }
                editStockIv.setOnClickListener {
                    onClickEdit.invoke(
                        CreateProductAlertRequest(
                            id = item.id,
                            productName = item.productName,
                            status = item.status,
                            productValue = item.productValue
                        )
                    )
                }
            }
        }
    }

}