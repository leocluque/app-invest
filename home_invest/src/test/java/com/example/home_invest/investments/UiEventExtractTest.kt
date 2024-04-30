package com.example.home_invest.investments

import com.example.home_invest.ui.home.investments.UiEventExtract
import com.example.network.data.response.ExtractResponse
import com.example.network.data.response.TransactionType
import org.junit.Assert.assertEquals
import org.junit.Test

class UiEventExtractTest {

    @Test
    fun `test UiEventExtract Success`() {
        val extractData = listOf(
            ExtractResponse("Transaction 1", "",10.0, TransactionType.EXPENSE, 10.0),
            ExtractResponse("Transaction 2", "", 10.0, TransactionType.EXPENSE, 10.0)
        )

        val event = UiEventExtract.Success(extractData)

        assertEquals(extractData, event.extract)
    }

    @Test
    fun `test UiEventExtract Loading`() {
        // Dado
        val isLoading = true

        // Quando
        val event = UiEventExtract.Loading(isLoading)

        // Então
        assertEquals(isLoading, event.isLoading)
    }

    @Test
    fun `test UiEventExtract Error`() {
        val errorMessage = "An error occurred"

        val event = UiEventExtract.Error(errorMessage)

        assertEquals(errorMessage, event.error)
    }
}
