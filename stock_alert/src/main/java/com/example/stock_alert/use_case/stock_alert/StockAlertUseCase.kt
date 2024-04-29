package com.example.stock_alert.use_case.stock_alert

import com.example.network.Resource
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductsResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface StockAlertUseCase {

    fun getAllAlerts(): Flow<Resource<List<ProductsResponse>>>

    fun createAlert(request: CreateProductAlertRequest): Flow<Resource<ResponseBody>>

    fun updateAlert(request: CreateProductAlertRequest): Flow<Resource<ResponseBody>>

    fun deleteAlert(request: CreateProductAlertRequest): Flow<Resource<ResponseBody>>
}