package com.example.network.data.response

data class InvestmentsResponse(
    val totalInvested: Double,
    val contractedProducts: List<ContractedProducts>
)

data class ContractedProducts(
    val productName: String,
    val productValue: Double,
    val totalInvested: Double,
    var percentage: Double = 0.0,
    var color: String
)