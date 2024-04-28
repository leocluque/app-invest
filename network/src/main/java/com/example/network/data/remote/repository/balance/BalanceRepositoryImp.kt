package com.example.network.data.remote.repository.balance

import com.example.network.data.remote.service.BalanceService
import com.example.network.data.response.BalanceResponse

class BalanceRepositoryImp(private val service: BalanceService) : BalanceRepository {
    override suspend fun getBalance(): BalanceResponse {
        return service.getBalance()
    }
}