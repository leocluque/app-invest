package com.example.home_invest.use_cases.extract

import com.example.network.NetworkConstants
import com.example.network.Resource
import com.example.network.data.remote.repository.extract.ExtractRepository
import com.example.network.data.response.ExtractResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ExtractUseCaseImp(private val repository: ExtractRepository) : ExtractUseCase {

    override fun getExtract(): Flow<Resource<ExtractResponse>> = flow {
        try {
            emit(Resource.Loading)
            val data = repository.getExtract()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: NetworkConstants.UNKNOW_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(NetworkConstants.INTERNET_ERROR))
        }
    }
}