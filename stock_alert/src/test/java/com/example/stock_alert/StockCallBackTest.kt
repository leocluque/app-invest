package com.example.stock_alert

import com.example.network.data.response.ProductStatus
import com.example.network.data.response.ProductsResponse
import com.example.stock_alert.ui.stock_alert.StockCallBack
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StockCallBackTest {

    private lateinit var callBack: StockCallBack
    private lateinit var oldList: List<ProductsResponse>
    private lateinit var newList: List<ProductsResponse>

    @Before
    fun setUp() {
        oldList = listOf(
            ProductsResponse("1", "Product 1", ProductStatus.AVAILABLE, 10.0),
            ProductsResponse("2", "Product 2",ProductStatus.AVAILABLE, 10.0),
            ProductsResponse("3", "Product 3",ProductStatus.AVAILABLE, 10.0)
        )
        newList = listOf(
            ProductsResponse("1", "Product 1",ProductStatus.AVAILABLE, 10.0),
            ProductsResponse("4", "Product 4",ProductStatus.AVAILABLE, 10.0),
            ProductsResponse("3", "Product 3",ProductStatus.AVAILABLE, 10.0)
        )
        callBack = StockCallBack(oldList, newList)
    }

    @Test
    fun `test areItemsTheSame - same item`() {
        // Itens antigos e novos possuem o mesmo ID
        val result = callBack.areItemsTheSame(0, 0)
        assertEquals(true, result)
    }

    @Test
    fun `test areItemsTheSame - different items`() {
        // Itens antigos e novos possuem IDs diferentes
        val result = callBack.areItemsTheSame(1, 1)
        assertEquals(false, result)
    }

    @Test
    fun `test getOldListSize`() {
        val result = callBack.getOldListSize()
        assertEquals(3, result)
    }

    @Test
    fun `test getNewListSize`() {
        val result = callBack.getNewListSize()
        assertEquals(3, result)
    }

    @Test
    fun `test areContentsTheSame - same content`() {
        // Conteúdos dos itens antigos e novos são iguais
        val result = callBack.areContentsTheSame(0, 0)
        assertEquals(true, result)
    }

    @Test
    fun `test areContentsTheSame - different content`() {
        // Conteúdos dos itens antigos e novos são diferentes
        val result = callBack.areContentsTheSame(0, 1)
        assertEquals(false, result)
    }
}
