package com.example.stock_alert.ui.stock_alert

import com.example.network.data.response.ProductsResponse

sealed class UiEventStock {
    data class Success(var products: List<ProductsResponse>) : UiEventStock()
    data class Loading(var isLoading: Boolean = false) : UiEventStock()
    data class Error(var error: String = "") : UiEventStock()
}