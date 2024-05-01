package com.example.stock_alert.util

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyMaskTest {

    @Test
    fun `test unmask - removes non-numeric characters`() {
        val input = "$1,000.00"

        val result = CurrencyMask.unmask(input)

        val expected = "100000"
        assertEquals(expected, result)
    }

    @Test
    fun `test unmask - handles empty string`() {
        val input = ""

        val result = CurrencyMask.unmask(input)

        val expected = ""
        assertEquals(expected, result)
    }

    @Test
    fun `test unmask - handles string with no numeric characters`() {
        val input = "abc"

        val result = CurrencyMask.unmask(input)

        val expected = ""
        assertEquals(expected, result)
    }
}
