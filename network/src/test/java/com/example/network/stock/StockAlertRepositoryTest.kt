package com.example.network.stock

import com.example.network.data.remote.repository.stock.StockAlertRepository
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductStatus
import com.example.network.data.response.ProductsResponse
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class StockAlertRepositoryTest {

    // Mock da resposta de alertas
    private val alertsResponse = listOf(ProductsResponse("", "", ProductStatus.AVAILABLE, 10.0))

    // Mock do repositório
    private val repository = mock(StockAlertRepository::class.java)

    // Mock do request para criar, atualizar e deletar alertas
    private val createRequest = CreateProductAlertRequest("", "", ProductStatus.AVAILABLE, 10.0)

    @Test
    fun `test getAllAlerts`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(repository.getAllAlerts()).thenReturn(alertsResponse)

        // Chamar o método e obter o resultado
        val result = repository.getAllAlerts()

        // Verificar se o resultado é o esperado
        assertEquals(alertsResponse, result)
    }

    @Test
    fun `test createAlert`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(repository.createAlert(createRequest)).thenReturn(
            ResponseBody.create(
                null,
                "Success"
            )
        )

        // Chamar o método e obter o resultado
        val result = repository.createAlert(createRequest)

        // Verificar se o resultado é o esperado
        assertEquals("Success", result.string())
    }

    @Test
    fun `test updateAlert`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(repository.updateAlert(createRequest)).thenReturn(
            ResponseBody.create(
                null,
                "Success"
            )
        )

        // Chamar o método e obter o resultado
        val result = repository.updateAlert(createRequest)

        // Verificar se o resultado é o esperado
        assertEquals("Success", result.string())
    }

    @Test
    fun `test deleteAlert`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(repository.deleteAlert(createRequest)).thenReturn(
            ResponseBody.create(
                null,
                "Success"
            )
        )

        // Chamar o método e obter o resultado
        val result = repository.deleteAlert(createRequest)

        // Verificar se o resultado é o esperado
        assertEquals("Success", result.string())
    }
}
