package com.example.stock_alert.use_case.stock_alert

import com.example.network.NetworkConstants
import com.example.network.Resource
import com.example.network.data.remote.repository.stock.StockAlertRepository
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

class StockAlertUseCaseImp(private val repository: StockAlertRepository) : StockAlertUseCase {

    override fun getAllAlerts(): Flow<Resource<List<ProductsResponse>>> = flow {
        try {
            emit(Resource.Loading)
            val data = repository.getAllAlerts()
            emit(Resource.Success(data))
        } catch (e: IOException) {
            emit(Resource.Error(NetworkConstants.INTERNET_ERROR))
        }
    }

    override fun createAlert(request: CreateProductAlertRequest): Flow<Resource<ResponseBody>> =
        flow {
            try {
                emit(Resource.Loading)
                val data = repository.createAlert(request)
                emit(Resource.Success(data))
            } catch (e: IOException) {
                emit(Resource.Error(NetworkConstants.INTERNET_ERROR))
            }
        }

    override fun updateAlert(request: CreateProductAlertRequest): Flow<Resource<ResponseBody>> =
        flow {
            try {
                emit(Resource.Loading)
                val data = repository.updateAlert(request)
                emit(Resource.Success(data))
            } catch (e: IOException) {
                emit(Resource.Error(NetworkConstants.INTERNET_ERROR))
            }
        }

    override fun deleteAlert(request: CreateProductAlertRequest): Flow<Resource<ResponseBody>> =
        flow {
            try {
                emit(Resource.Loading)
                val data = repository.deleteAlert(request)
                emit(Resource.Success(data))
            } catch (e: IOException) {
                emit(Resource.Error(NetworkConstants.INTERNET_ERROR))
            }
        }
}