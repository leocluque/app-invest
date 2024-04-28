package com.example.network.data.remote.repository.stock

import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductsResponse
import okhttp3.ResponseBody

interface StockAlertRepository {
    suspend fun getAllAlerts(): List<ProductsResponse>

    suspend fun createAlert(request: CreateProductAlertRequest): ResponseBody

    suspend fun updateAlert(request: CreateProductAlertRequest): ResponseBody

    suspend fun deleteAlert(request: CreateProductAlertRequest): ResponseBody
}