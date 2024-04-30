package com.example.home_invest.data.model

import com.example.home_invest.ui.components.ProgressItem
import com.example.network.data.response.ContractedProducts

fun ContractedProducts.toProgressItem(): ProgressItem {
    return ProgressItem(
        productName = productName,
        percentage = percentage.toInt().toFloat(),
        color = color
    )
}