package com.example.network.stock

import com.example.network.data.remote.repository.stock.StockAlertRepositoryImp
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

class StockAlertRepositoryImpTest {

    // Mock do StockAlertService
    private val mockService = mock(StockAlertService::class.java)

    // Instância do objeto a ser testado
    private val repository = StockAlertRepositoryImp(mockService)

    @Test
    fun `test getAllAlerts() success`() {
        // Resposta esperada do serviço
        val expectedAlertsResponse = listOf(
            ProductsResponse(
                id = "",
                productValue = 10.0,
                productName = "",
                status = ProductStatus.AVAILABLE

            )
        )

        // Configuração do comportamento do mock
        runBlocking {
            `when`(mockService.getAllAlerts()).thenReturn(expectedAlertsResponse)
        }

        // Chamar o método que estamos testando
        val actualAlertsResponse = runBlocking {
            repository.getAllAlerts()
        }

        // Verificar se o resultado retornado é o esperado
        assertEquals(expectedAlertsResponse, actualAlertsResponse)
    }

    @Test(expected = Exception::class)
    fun `test getAllAlerts() throws Exception`() {
        // Configuração do comportamento do mock para lançar uma exceção
        runBlocking {
            `when`(mockService.getAllAlerts()).thenThrow(Exception())
        }

        // Chamar o método que estamos testando, deve lançar uma exceção
        runBlocking {
            repository.getAllAlerts()
        }
    }

    @Test
    fun `test createAlert() success`() {
        // Request de exemplo para criar alerta
        val request = CreateProductAlertRequest(
            "",
            "", ProductStatus.AVAILABLE, 10.0
        )

        // Resposta esperada do serviço
        val expectedResponseBody = ResponseBody.create(null, "")

        // Configuração do comportamento do mock
        runBlocking {
            `when`(mockService.createAlert(request)).thenReturn(expectedResponseBody)
        }

        // Chamar o método que estamos testando
        val actualResponseBody = runBlocking {
            repository.createAlert(request)
        }

        // Verificar se o resultado retornado é o esperado
        assertEquals(expectedResponseBody, actualResponseBody)
    }

    @Test(expected = Exception::class)
    fun `test createAlert() throws Exception`() {
        // Request de exemplo para criar alerta
        val request = CreateProductAlertRequest(
            "",
            "", ProductStatus.AVAILABLE, 10.0
        )
        // Configuração do comportamento do mock para lançar uma exceção
        runBlocking {
            `when`(mockService.createAlert(request)).thenThrow(Exception())
        }

        // Chamar o método que estamos testando, deve lançar uma exceção
        runBlocking {
            repository.createAlert(request)
        }
    }

    @Test(expected = Exception::class)
    fun `test updateAlert() throws Exception`() {
        // Request de exemplo para atualizar alerta
        val request = CreateProductAlertRequest(
            "",
            "", ProductStatus.AVAILABLE, 10.0
        )

        // Configuração do comportamento do mock para lançar uma exceção
        runBlocking {
            `when`(mockService.updateAlert(request)).thenThrow(Exception())
        }

        // Chamar o método que estamos testando, deve lançar uma exceção
        runBlocking {
            repository.updateAlert(request)
        }
    }

    @Test
    fun `test updateAlert() success`() {
        // Request de exemplo para atualizar alerta
        val request = CreateProductAlertRequest(
            "",
            "", ProductStatus.AVAILABLE, 10.0
        )
        // Resposta esperada do serviço
        val expectedResponseBody = ResponseBody.create(null, "")

        // Configuração do comportamento do mock
        runBlocking {
            `when`(mockService.updateAlert(request)).thenReturn(expectedResponseBody)
        }

        // Chamar o método que estamos testando
        val actualResponseBody = runBlocking {
            repository.updateAlert(request)
        }

        // Verificar se o resultado retornado é o esperado
        assertEquals(expectedResponseBody, actualResponseBody)
    }

    @Test(expected = Exception::class)
    fun `test deleteAlert() throws Exception`() {
        // Request de exemplo para deletar alerta
        val request = CreateProductAlertRequest(
            "",
            "", ProductStatus.AVAILABLE, 10.0
        )
        // Configuração do comportamento do mock para lançar uma exceção
        runBlocking {
            `when`(mockService.deleteAlert(request)).thenThrow(Exception())
        }

        // Chamar o método que estamos testando, deve lançar uma exceção
        runBlocking {
            repository.deleteAlert(request)
        }
    }

    @Test
    fun `test deleteAlert() success`() {
        // Request de exemplo para deletar alerta
        val request = CreateProductAlertRequest(
            "",
            "", ProductStatus.AVAILABLE, 10.0
        )
        // Resposta esperada do serviço
        val expectedResponseBody = ResponseBody.create(null, "")

        // Configuração do comportamento do mock
        runBlocking {
            `when`(mockService.deleteAlert(request)).thenReturn(expectedResponseBody)
        }

        // Chamar o método que estamos testando
        val actualResponseBody = runBlocking {
            repository.deleteAlert(request)
        }

        // Verificar se o resultado retornado é o esperado
        assertEquals(expectedResponseBody, actualResponseBody)
    }
}
