package com.example.home_invest.model

import com.example.home_invest.data.model.toProgressItem
import com.example.network.data.response.ContractedProducts
import org.junit.Assert.assertEquals
import org.junit.Test

class ContractedProductsTest {

    @Test
    fun `test toProgressItem`() {
        // Dados de entrada
        val productName = "Product A"
        val percentage = 75.0
        val color = "#FFA500" // Cor laranja

        // Chama a função
        val progressItem = ContractedProducts(
            productName = productName,
            productValue = 10.9,
            totalInvested = 10.0,
            percentage = percentage,
            color
        ).toProgressItem()

        // Verifica se o resultado está correto
        assertEquals(productName, progressItem.productName)
        assertEquals(percentage.toInt().toFloat(), progressItem.percentage)
        assertEquals(color, progressItem.color)
    }
}
