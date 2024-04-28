package com.example.network.data.remote.service

import com.example.network.data.response.InvestmentsResponse
import retrofit2.http.GET

interface InvestmentsService {

    @GET("getInvestments")
    suspend fun getInvestments(): InvestmentsResponse
}