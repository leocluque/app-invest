package com.example.home_invest.use_cases.balance

import com.example.network.Resource
import com.example.network.data.response.BalanceResponse
import kotlinx.coroutines.flow.Flow

interface BalanceUseCase {

    fun getBalance(): Flow<Resource<BalanceResponse>>
}