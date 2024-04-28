package com.example.network.data.remote.repository.investments

import com.example.network.data.response.InvestmentsResponse

interface InvestmentsRepository {
    suspend fun getInvestments(): InvestmentsResponse

}