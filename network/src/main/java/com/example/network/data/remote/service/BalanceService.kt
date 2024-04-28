package com.example.network.data.remote.service

import com.example.network.data.response.BalanceResponse
import retrofit2.http.GET

interface BalanceService {

    @GET("getBalance")
    suspend fun getBalance(): BalanceResponse

}