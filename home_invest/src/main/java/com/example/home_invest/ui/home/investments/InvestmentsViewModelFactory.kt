package com.example.home_invest.ui.home.investments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home_invest.builder.HomeBuilder
import com.example.home_invest.use_cases.extract.ExtractUseCase
import com.example.home_invest.use_cases.extract.ExtractUseCaseImp

class InvestmentsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val extractRepository = HomeBuilder.getExtractRepository()
        val useCaseExtract: ExtractUseCase? = extractRepository?.let { ExtractUseCaseImp(it) }

        return InvestmentsViewModel(useCaseExtract!!) as T
    }
}