package com.example.home_invest.ui.home.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home_invest.builder.HomeBuilder
import com.example.home_invest.use_cases.extract.ExtractUseCase
import com.example.home_invest.use_cases.extract.ExtractUseCaseImp
import com.example.home_invest.use_cases.investments.InvestmentsUseCase
import com.example.home_invest.use_cases.investments.InvestmentsUseCaseImp

class ProductViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repositoryInvestments = HomeBuilder.getInvestmentsRepository()
        val useCaseInvestments: InvestmentsUseCase? =
            repositoryInvestments?.let { InvestmentsUseCaseImp(it) }

        return ProductViewModel(useCaseInvestments!!) as T
    }
}