package com.example.stock_alert.ui.stock_alert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stock_alert.builder.StockAlertBuilder
import com.example.stock_alert.use_case.stock_alert.StockAlertUseCase
import com.example.stock_alert.use_case.stock_alert.StockAlertUseCaseImp

class StockAlertViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = StockAlertBuilder.getStockAlertRepository()
        val useCase: StockAlertUseCase? = repository?.let { StockAlertUseCaseImp(it) }
        return useCase?.let { StockAlertViewModel(it) } as T
    }
}