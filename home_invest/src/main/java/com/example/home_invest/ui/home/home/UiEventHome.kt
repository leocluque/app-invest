package com.example.home_invest.ui.home.home

import com.example.network.data.response.BalanceResponse

sealed class UiEventHome {
    data class Success(var balance: BalanceResponse) : UiEventHome()
    data class Loading(var isLoading: Boolean = false) : UiEventHome()
    data class Error(var error: String = "") : UiEventHome()
}