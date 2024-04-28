package com.example.network.data.remote.service

import com.example.network.data.response.ExtractResponse
import retrofit2.http.GET

interface ExtractService {

    @GET("getExtract")
    suspend fun getExtract(): ExtractResponse

}