package com.example.network.data.remote.repository.stock

import com.example.network.data.remote.service.StockAlertService
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductsResponse
import okhttp3.ResponseBody

class StockAlertRepositoryImp(private val service: StockAlertService) : StockAlertRepository {
    override suspend fun getAllAlerts(): List<ProductsResponse> {
        return service.getAllAlerts()
    }

    override suspend fun createAlert(request: CreateProductAlertRequest): ResponseBody {
        return service.createAlert(request)
    }

    override suspend fun updateAlert(request: CreateProductAlertRequest): ResponseBody {
        return service.updateAlert(request)
    }

    override suspend fun deleteAlert(request: CreateProductAlertRequest): ResponseBody {
        return service.deleteAlert(request)
    }
}