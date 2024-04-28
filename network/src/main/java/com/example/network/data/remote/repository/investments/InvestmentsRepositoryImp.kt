package com.example.network.data.remote.repository.investments

import com.example.network.data.remote.service.InvestmentsService
import com.example.network.data.response.InvestmentsResponse

class InvestmentsRepositoryImp(private val service: InvestmentsService) : InvestmentsRepository {
    override suspend fun getInvestments(): InvestmentsResponse {
        return service.getInvestments()
    }

}