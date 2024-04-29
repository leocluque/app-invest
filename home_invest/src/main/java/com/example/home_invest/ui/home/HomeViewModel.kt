package com.example.home_invest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_invest.use_cases.balance.BalanceUseCase
import com.example.network.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val balanceUseCase: BalanceUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEventHome>()
    val uiEvent: SharedFlow<UiEventHome> = _uiEvent.asSharedFlow()

    fun getBalance() {
        balanceUseCase.getBalance().onEach { result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _uiEvent.emit(UiEventHome.Loading(false))
                        _uiEvent.emit(UiEventHome.Success(result.data))
                    }

                    is Resource.Loading -> {
                        _uiEvent.emit(UiEventHome.Loading(true))
                    }

                    is Resource.Error -> {
                        _uiEvent.emit(UiEventHome.Loading(false))
                        _uiEvent.emit(UiEventHome.Error(result.message))
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

}