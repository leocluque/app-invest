package com.example.network.data.remote.repository.extract

import com.example.network.data.remote.service.ExtractService
import com.example.network.data.response.ExtractResponse

class ExtractRepositoryImp(private val service: ExtractService) : ExtractRepository {
    override suspend fun getExtract(): ExtractResponse {
        return service.getExtract()
    }
}