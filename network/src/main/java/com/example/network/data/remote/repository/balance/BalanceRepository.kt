package com.example.network.data.remote.repository.balance

import com.example.network.data.response.BalanceResponse

interface BalanceRepository {
    suspend fun getBalance(): BalanceResponse
}