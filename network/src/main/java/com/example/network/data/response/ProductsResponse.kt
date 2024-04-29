package com.example.network.data.response

data class ProductsResponse(
    val id: String,
    val productName: String,
    val status: ProductStatus,
    val productValue: Double
)

enum class ProductStatus {
    AVAILABLE,
    NOT_AVAILABLE
}