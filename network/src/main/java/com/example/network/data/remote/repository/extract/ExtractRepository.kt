package com.example.network.data.remote.repository.extract

import com.example.network.data.response.ExtractResponse

interface ExtractRepository {
    suspend fun getExtract(): List<ExtractResponse>
}