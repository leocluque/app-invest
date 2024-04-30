package com.example.home_invest.investments

import com.example.home_invest.ui.home.investments.UiEventInvestments
import com.example.network.data.response.InvestmentsResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UiEventInvestmentsTest {

    @Test
    fun `test UiEventInvestments Success`() {
        // Dado
        val investmentsData = InvestmentsResponse(10.0, listOf())

        // Quando
        val event = UiEventInvestments.Success(investmentsData)

        // Então
        assertEquals(investmentsData, event.investments)
    }

    @Test
    fun `test UiEventInvestments Loading`() {
        // Dado
        val isLoading = true

        // Quando
        val event = UiEventInvestments.Loading(isLoading)

        // Então
        assertEquals(isLoading, event.isLoading)
    }

    @Test
    fun `test UiEventInvestments Error`() {
        // Dado
        val errorMessage = "An error occurred"

        // Quando
        val event = UiEventInvestments.Error(errorMessage)

        // Então
        assertEquals(errorMessage, event.error)
    }
}