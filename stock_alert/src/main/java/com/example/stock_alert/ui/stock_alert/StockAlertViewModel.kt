package com.example.stock_alert.ui.stock_alert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.Resource
import com.example.network.data.request.CreateProductAlertRequest
import com.example.stock_alert.use_case.stock_alert.StockAlertUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class StockAlertViewModel(
    private val stockAlertUseCase: StockAlertUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEventStock>()
    val uiEvent: SharedFlow<UiEventStock> = _uiEvent.asSharedFlow()

    private val _uiEventBasic = MutableSharedFlow<UiEventStockBasic>()
    val uiEventBasic: SharedFlow<UiEventStockBasic> = _uiEventBasic.asSharedFlow()

    fun getAllAlerts() {
        stockAlertUseCase.getAllAlerts().onEach { result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _uiEvent.emit(UiEventStock.Loading(false))
                        _uiEvent.emit(UiEventStock.Success(result.data))
                    }

                    is Resource.Loading -> {
                        _uiEvent.emit(UiEventStock.Loading(true))
                    }

                    is Resource.Error -> {
                        _uiEvent.emit(UiEventStock.Loading(false))
                        _uiEvent.emit(UiEventStock.Error(result.message))
                    }
                }
            }

        }.launchIn(viewModelScope)
    }


    fun createAlert(request: CreateProductAlertRequest) {
        stockAlertUseCase.createAlert(request).onEach { result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(false))
                        _uiEventBasic.emit(UiEventStockBasic.Success)
                    }

                    is Resource.Loading -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(true))
                    }

                    is Resource.Error -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(false))
                        _uiEventBasic.emit(UiEventStockBasic.Error(result.message))
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun updateAlert(request: CreateProductAlertRequest) {
        stockAlertUseCase.updateAlert(request).onEach { result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(false))
                        _uiEventBasic.emit(UiEventStockBasic.Success)
                    }

                    is Resource.Loading -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(true))
                    }

                    is Resource.Error -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(false))
                        _uiEventBasic.emit(UiEventStockBasic.Error(result.message))
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun deleteAlert(request: CreateProductAlertRequest) {
        stockAlertUseCase.deleteAlert(request).onEach { result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(false))
                        _uiEventBasic.emit(UiEventStockBasic.Success)
                    }

                    is Resource.Loading -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(true))
                    }

                    is Resource.Error -> {
                        _uiEventBasic.emit(UiEventStockBasic.Loading(false))
                        _uiEventBasic.emit(UiEventStockBasic.Error(result.message))
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

}