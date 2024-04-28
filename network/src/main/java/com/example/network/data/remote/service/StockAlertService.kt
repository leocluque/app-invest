package com.example.network.data.remote.service

import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductsResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StockAlertService {

    @GET("listProducts")
    suspend fun getAllAlerts(): List<ProductsResponse>

    @POST("createProductAlert")
    suspend fun createAlert(@Body request: CreateProductAlertRequest): ResponseBody

    @POST("updateProductAlert")
    suspend fun updateAlert(@Body request: CreateProductAlertRequest): ResponseBody

    @POST("deleteProductAlert")
    suspend fun deleteAlert(@Body request: CreateProductAlertRequest): ResponseBody

}