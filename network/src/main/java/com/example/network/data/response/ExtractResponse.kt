package com.example.network.data.response

data class ExtractResponse(
    val name: String,
    val date: String,
    val amount: Double,
    val type: TransactionType,
    val balanceOfDay: Double
)

enum class TransactionType {
    INCOME,
    EXPENSE
}