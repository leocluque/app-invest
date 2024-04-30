package com.example.home_invest.ui.home.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_invest.ui.home.investments.UiEventInvestments
import com.example.home_invest.use_cases.investments.InvestmentsUseCase
import com.example.network.Resource
import com.example.network.data.response.ContractedProducts
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProductViewModel(
    private val investmentsUseCase: InvestmentsUseCase,
) : ViewModel() {

    private val _uiEventInvestments = MutableLiveData<UiEventInvestments>()
    val uiEventInvestments: LiveData<UiEventInvestments> = _uiEventInvestments
    private var listProducts = mutableListOf<ContractedProducts>()

    fun getInvestments() {
        investmentsUseCase.getInvestments().onEach { result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _uiEventInvestments.postValue(UiEventInvestments.Loading(false))
                        _uiEventInvestments.postValue(UiEventInvestments.Success(result.data))
                    }

                    is Resource.Loading -> {
                        _uiEventInvestments.postValue(UiEventInvestments.Loading(true))
                    }

                    is Resource.Error -> {
                        _uiEventInvestments.postValue(UiEventInvestments.Loading(false))
                        _uiEventInvestments.postValue(UiEventInvestments.Error(result.message))
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getListInvestments() = listProducts
}