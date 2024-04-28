package com.example.network.data.request

import com.example.network.data.response.ProductStatus

data class CreateProductAlertRequest(
    var id: String? = "",
    val productName: String,
    val status: ProductStatus,
    val productValue: Double
)