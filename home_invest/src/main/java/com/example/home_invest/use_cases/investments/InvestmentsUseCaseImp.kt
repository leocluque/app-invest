package com.example.home_invest.use_cases.investments

import com.example.network.NetworkConstants
import com.example.network.Resource
import com.example.network.data.remote.repository.investments.InvestmentsRepository
import com.example.network.data.response.InvestmentsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class InvestmentsUseCaseImp(private val repository: InvestmentsRepository) : InvestmentsUseCase {

    override fun getInvestments(): Flow<Resource<InvestmentsResponse>> = flow {
        try {
            emit(Resource.Loading)
            val data = repository.getInvestments()
            emit(Resource.Success(data))
        } catch (e: IOException) {
            emit(Resource.Error(NetworkConstants.INTERNET_ERROR))
        }
    }
}