package com.example.home_invest

import com.example.home_invest.ui.home.home.UiEventHome
import com.example.network.data.response.BalanceResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UiEventHomeTest {

    @Test
    fun `Success_constructor_sets_balance`() {
        val expectedBalance = 1000.00
        val balanceResponse = BalanceResponse(expectedBalance)
        val successEvent = UiEventHome.Success(balanceResponse)

        assertEquals(expectedBalance, successEvent.balance.balance)
    }

    @Test
    fun `Loading_constructor_sets_isLoading`() {
        val isLoading = true
        val loadingEvent = UiEventHome.Loading(isLoading)

        assertEquals(isLoading, loadingEvent.isLoading)
    }

    @Test
    fun `Error_constructor_sets_error`() {
        val errorMessage = "Network Error"
        val errorEvent = UiEventHome.Error(errorMessage)

        assertEquals(errorMessage, errorEvent.error)
    }
}