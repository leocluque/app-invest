package com.example.stock_alert.builder

import com.example.network.data.remote.service.StockAlertService
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class StockAlertBuilderTest {


    private lateinit var stockService: StockAlertService

    @Before
    fun setUp() {
        // Mock dos serviços
        stockService = mock(StockAlertService::class.java)
    }

    @Test
    fun `test executeDepInject`() {
        StockAlertBuilder.executeDepInject()

        assertNotNull(StockAlertBuilder.getStockAlertRepository())
    }

    @Test
    fun `test provideStockAlertService`() {
        // Cria o repositório de saldo
        StockAlertBuilder.executeDepInject()
        val stockRepository = StockAlertBuilder.getStockAlertRepository()

        // Verifica se o repositório não é nulo
        assertNotNull(stockRepository)
    }

}
