package com.example.home_invest.ui.home.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home_invest.use_cases.investments.InvestmentsUseCaseImp
import com.example.network.data.remote.repository.investments.InvestmentsRepository

class ProductViewModelFactory(private val repository: InvestmentsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            val useCaseInvestments = InvestmentsUseCaseImp(repository)
            return ProductViewModel(useCaseInvestments) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
    }
}