package com.example.home_invest.investments

import com.example.home_invest.ui.home.investments.InvestmentsCallBack
import com.example.network.data.response.ContractedProducts
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class InvestmentsCallBackTest {

    @Test
    fun `test areItemsTheSame - true`() {
        val oldList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product A", 10.0, 10.0, 10.0, "")
        )
        val newList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product A", 10.0, 10.0, 10.0, "")
        )

        val callBack = InvestmentsCallBack(oldList, newList)

        assertTrue(callBack.areItemsTheSame(0, 0))
        assertTrue(callBack.areItemsTheSame(1, 1))
    }

    @Test
    fun `test areItemsTheSame - false`() {
        val oldList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product B", 10.0, 10.0, 10.0, "")
        )
        val newList = listOf(
            ContractedProducts("Product C", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product D", 10.0, 10.0, 10.0, "")
        )

        val callBack = InvestmentsCallBack(oldList, newList)

        assertFalse(callBack.areItemsTheSame(0, 1))
    }

    @Test
    fun `test getOldListSize`() {
        val oldList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product A", 10.0, 10.0, 10.0, "")
        )

        val callBack = InvestmentsCallBack(oldList, emptyList())

        assertEquals(2, callBack.oldListSize)
    }

    @Test
    fun `test getNewListSize`() {
        val newList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product A", 10.0, 10.0, 10.0, "")
        )

        val callBack = InvestmentsCallBack(emptyList(), newList)

        assertEquals(2, callBack.getNewListSize())
    }

    @Test
    fun `test areContentsTheSame - true`() {
        val oldList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product A", 10.0, 10.0, 10.0, "")
        )
        val newList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product A", 10.0, 10.0, 10.0, "")
        )

        val callBack = InvestmentsCallBack(oldList, newList)

        assertTrue(callBack.areContentsTheSame(0, 0))
        assertTrue(callBack.areContentsTheSame(1, 1))
    }

    @Test
    fun `test areContentsTheSame - false`() {
        val oldList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product B", 10.0, 10.0, 10.0, "")
        )
        val newList = listOf(
            ContractedProducts("Product A", 10.0, 10.0, 10.0, ""),
            ContractedProducts("Product C", 10.0, 10.0, 10.0, "")
        )

        val callBack = InvestmentsCallBack(oldList, newList)

        assertFalse(callBack.areContentsTheSame(0, 1))
    }
}
