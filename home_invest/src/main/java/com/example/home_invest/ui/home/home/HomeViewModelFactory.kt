package com.example.home_invest.ui.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home_invest.builder.HomeBuilder
import com.example.home_invest.use_cases.balance.BalanceUseCaseImp
import com.example.network.data.remote.repository.balance.BalanceRepository

class HomeViewModelFactory(private val balanceRepository: BalanceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val useCase = BalanceUseCaseImp(balanceRepository)
            return HomeViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
    }
}