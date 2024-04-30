package com.example.home_invest.ui.home.investments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home_invest.use_cases.extract.ExtractUseCaseImp
import com.example.network.data.remote.repository.extract.ExtractRepository

class InvestmentsViewModelFactory(private val repository: ExtractRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val useCaseExtract = ExtractUseCaseImp(repository)
        return InvestmentsViewModel(useCaseExtract!!) as T
    }
}