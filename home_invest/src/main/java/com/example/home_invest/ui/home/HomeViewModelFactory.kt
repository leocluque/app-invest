package com.example.home_invest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home_invest.builder.HomeBuilder
import com.example.home_invest.use_cases.balance.BalanceUseCase
import com.example.home_invest.use_cases.balance.BalanceUseCaseImp

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = HomeBuilder.getBalanceRepository()
        val useCase: BalanceUseCase? = repository?.let { BalanceUseCaseImp(it) }
        return useCase?.let { HomeViewModel(it) } as T
    }
}