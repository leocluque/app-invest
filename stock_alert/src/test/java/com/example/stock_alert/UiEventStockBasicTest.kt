package com.example.stock_alert

import com.example.stock_alert.ui.stock_alert.UiEventStockBasic
import org.junit.Assert.assertEquals
import org.junit.Test

class UiEventStockBasicTest {

    @Test
    fun `test UiEventStockBasic Success`() {
        // Act
        val uiEvent = UiEventStockBasic.Success

        // Assert
        assertEquals(UiEventStockBasic.Success, uiEvent)
    }

    @Test
    fun `test UiEventStockBasic Loading`() {
        // Act
        val uiEvent = UiEventStockBasic.Loading(true)

        // Assert
        assertEquals(true, uiEvent.isLoading)
    }

    @Test
    fun `test UiEventStockBasic Error`() {
        // Arrange
        val errorMessage = "An error occurred"

        // Act
        val uiEvent = UiEventStockBasic.Error(errorMessage)

        // Assert
        assertEquals(errorMessage, uiEvent.error)
    }
}
