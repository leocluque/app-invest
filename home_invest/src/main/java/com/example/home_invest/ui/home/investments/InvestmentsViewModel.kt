package com.example.home_invest.ui.home.investments

import androidx.lifecycle.ViewModel
import com.example.home_invest.use_cases.extract.ExtractUseCase
import com.example.home_invest.use_cases.investments.InvestmentsUseCase

class InvestmentsViewModel(
    private val investmentsUseCase: InvestmentsUseCase,
    private val extractUseCase: ExtractUseCase
) : ViewModel() {



}