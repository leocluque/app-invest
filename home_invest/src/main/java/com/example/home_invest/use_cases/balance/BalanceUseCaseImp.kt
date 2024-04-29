package com.example.home_invest.use_cases.balance

import com.example.network.NetworkConstants
import com.example.network.Resource
import com.example.network.data.remote.repository.balance.BalanceRepository
import com.example.network.data.response.BalanceResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class BalanceUseCaseImp(private val repository: BalanceRepository) : BalanceUseCase {

    override fun getBalance(): Flow<Resource<BalanceResponse>> = flow {
        try {
            emit(Resource.Loading)
            val data = repository.getBalance()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: NetworkConstants.UNKNOW_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(NetworkConstants.INTERNET_ERROR))
        }
    }
}