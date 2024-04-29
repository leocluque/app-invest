package com.example.home_invest.ui.home.investments

import com.example.network.data.response.ExtractResponse

sealed class UiEventExtract {
    data class Success(var extract: List<ExtractResponse>) : UiEventExtract()
    data class Loading(var isLoading: Boolean = false) : UiEventExtract()
    data class Error(var error: String = "") : UiEventExtract()
}