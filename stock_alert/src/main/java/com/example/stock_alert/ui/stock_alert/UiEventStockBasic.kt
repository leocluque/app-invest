package com.example.stock_alert.ui.stock_alert

sealed class UiEventStockBasic {
    object Success : UiEventStockBasic()
    data class Loading(var isLoading: Boolean = false) : UiEventStockBasic()
    data class Error(var error: String = "") : UiEventStockBasic()
}