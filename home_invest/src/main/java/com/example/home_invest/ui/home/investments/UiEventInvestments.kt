package com.example.home_invest.ui.home.investments

import com.example.network.data.response.InvestmentsResponse

sealed class UiEventInvestments {
    data class Success(var investments: InvestmentsResponse) : UiEventInvestments()
    data class Loading(var isLoading: Boolean = false) : UiEventInvestments()
    data class Error(var error: String = "") : UiEventInvestments()
}