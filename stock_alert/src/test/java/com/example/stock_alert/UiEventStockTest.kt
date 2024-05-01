package com.example.stock_alert

import com.example.network.data.response.ProductStatus
import com.example.network.data.response.ProductsResponse
import com.example.stock_alert.ui.stock_alert.UiEventStock
import org.junit.Assert.assertEquals
import org.junit.Test

class UiEventStockTest {

    @Test
    fun `test UiEventStock Success`() {
        val products = listOf(
            ProductsResponse("1", "Product 1", ProductStatus.AVAILABLE, 10.0),
            ProductsResponse("2", "Product 2", ProductStatus.AVAILABLE, 10.0)
        )

        val uiEvent = UiEventStock.Success(products)

        assertEquals(products, uiEvent.products)
    }

    @Test
    fun `test UiEventStock Loading`() {
        val uiEvent = UiEventStock.Loading(true)

        assertEquals(true, uiEvent.isLoading)
    }

    @Test
    fun `test UiEventStock Error`() {
        val errorMessage = "An error occurred"

        val uiEvent = UiEventStock.Error(errorMessage)

        assertEquals(errorMessage, uiEvent.error)
    }
}
