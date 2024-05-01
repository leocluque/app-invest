package com.example.stock_alert.ui.stock_alert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.network.data.remote.repository.stock.StockAlertRepository
import com.example.stock_alert.use_case.stock_alert.StockAlertUseCaseImp

class StockAlertViewModelFactory(private val repository: StockAlertRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockAlertViewModel::class.java)) {
            val useCase = StockAlertUseCaseImp(repository)
            return StockAlertViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
    }
}