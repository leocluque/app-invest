package com.example.home_invest.use_cases.investments

import com.example.network.Resource
import com.example.network.data.response.InvestmentsResponse
import kotlinx.coroutines.flow.Flow

interface InvestmentsUseCase {
    fun getInvestments(): Flow<Resource<InvestmentsResponse>>
}