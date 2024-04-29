package com.example.home_invest.use_cases.extract

import com.example.network.Resource
import com.example.network.data.response.ExtractResponse
import kotlinx.coroutines.flow.Flow

interface ExtractUseCase {
    fun getExtract(): Flow<Resource<ExtractResponse>>
}