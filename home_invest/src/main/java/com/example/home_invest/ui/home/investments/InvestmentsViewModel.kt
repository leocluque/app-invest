package com.example.home_invest.ui.home.investments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_invest.use_cases.extract.ExtractUseCase
import com.example.home_invest.use_cases.investments.InvestmentsUseCase
import com.example.network.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class InvestmentsViewModel(
    private val extractUseCase: ExtractUseCase
) : ViewModel() {

    private val _uiEventExtract = MutableSharedFlow<UiEventExtract>()
    val uiEventExtract: SharedFlow<UiEventExtract> = _uiEventExtract.asSharedFlow()

    fun getExtract() {
        extractUseCase.getExtract().onEach { result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _uiEventExtract.emit(UiEventExtract.Loading(false))
                        _uiEventExtract.emit(UiEventExtract.Success(result.data))
                    }

                    is Resource.Loading -> {
                        _uiEventExtract.emit(UiEventExtract.Loading(true))
                    }

                    is Resource.Error -> {
                        _uiEventExtract.emit(UiEventExtract.Loading(false))
                        _uiEventExtract.emit(UiEventExtract.Error(result.message))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}