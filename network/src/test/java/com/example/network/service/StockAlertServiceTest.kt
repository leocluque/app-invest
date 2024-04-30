package com.example.network.service

import com.example.network.data.remote.service.StockAlertService
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductStatus
import com.example.network.data.response.ProductsResponse
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class StockAlertServiceTest {

    // Mock da resposta de produtos
    private val productsResponse = listOf(
        ProductsResponse(
            id = "",
            productName = "",
            status = ProductStatus.AVAILABLE,
            10.0
        )
    )

    // Mock do serviço
    private val service = mock(StockAlertService::class.java)

    @Test
    fun `test getAllAlerts`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(service.getAllAlerts()).thenReturn(productsResponse)

        // Chamar o método e obter o resultado
        val result = service.getAllAlerts()

        // Verificar se o resultado é o esperado
        assertEquals(productsResponse, result)
    }

    @Test
    fun `test createAlert`() = runBlocking {
        // Mock do corpo da solicitação
        val request = CreateProductAlertRequest(
            id = null,
            "",
            ProductStatus.AVAILABLE,
            10.0
        )
        // Mock da resposta
        val responseBody = mock(ResponseBody::class.java)
        `when`(service.createAlert(request)).thenReturn(responseBody)

        // Chamar o método e obter o resultado
        val result = service.createAlert(request)

        // Verificar se o resultado é o esperado
        assertEquals(responseBody, result)
    }

    @Test
    fun `test updateAlert`() = runBlocking {
        // Mock do corpo da solicitação
        val request = CreateProductAlertRequest(
            id = null,
            "",
            ProductStatus.AVAILABLE,
            10.0
        )
        // Mock da resposta
        val responseBody = mock(ResponseBody::class.java)
        `when`(service.updateAlert(request)).thenReturn(responseBody)

        // Chamar o método e obter o resultado
        val result = service.updateAlert(request)

        // Verificar se o resultado é o esperado
        assertEquals(responseBody, result)
    }

    @Test
    fun `test deleteAlert`() = runBlocking {
        // Mock do corpo da solicitação
        val request = CreateProductAlertRequest(
            id = null,
            "",
            ProductStatus.AVAILABLE,
            10.0
        )

        // Mock da resposta
        val responseBody = mock(ResponseBody::class.java)
        `when`(service.deleteAlert(request)).thenReturn(responseBody)

        // Chamar o método e obter o resultado
        val result = service.deleteAlert(request)

        // Verificar se o resultado é o esperado
        assertEquals(responseBody, result)
    }
}
