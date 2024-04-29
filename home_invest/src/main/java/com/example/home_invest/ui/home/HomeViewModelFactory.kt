package com.example.home_invest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home_invest.builder.HomeBuilder
import com.example.home_invest.use_cases.balance.BalanceUseCase
import com.example.home_invest.use_cases.balance.BalanceUseCaseImp

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val repository = HomeBuilder.getBalanceRepository()
            val useCase = repository?.let { BalanceUseCaseImp(it) }
            if (useCase != null) {
                return HomeViewModel(useCase) as T
            } else {
                // Handle the case where repository is null or useCase creation fails
                throw RuntimeException("Failed to create HomeViewModel")
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
    }
}