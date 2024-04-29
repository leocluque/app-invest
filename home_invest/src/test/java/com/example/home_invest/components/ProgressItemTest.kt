package com.example.home_invest.components

import com.example.home_invest.ui.components.ProgressItem
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ProgressItemTest {

    @Test
    fun `equals_ReturnsTrueForEqualObjects`() {
        val item1 = ProgressItem("Product 1", 25f, "#FF0000")
        val item2 = ProgressItem("Product 1", 25f, "#FF0000")

        assertEquals(item1, item2)
    }

    @Test
    fun `equals_ReturnsFalseForDifferentProperties`() {
        val item1 = ProgressItem("Product 1", 25f, "#FF0000")
        val item2 = ProgressItem("Product 2", 25f, "#FF0000")

        assertNotEquals(item1, item2)
    }
}