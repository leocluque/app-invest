package com.example.network.investments

import com.example.network.data.remote.repository.investments.InvestmentsRepositoryImp
import com.example.network.data.remote.service.InvestmentsService
import com.example.network.data.response.InvestmentsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class InvestmentsRepositoryImpTest {

    @Test
    fun `test getInvestments() success`() {
        // Mock do InvestmentsService
        val mockService = mock(InvestmentsService::class.java)

        // Instância do objeto a ser testado
        val repository = InvestmentsRepositoryImp(mockService)

        // Resposta esperada do serviço
        val expectedInvestmentsResponse = InvestmentsResponse(
            totalInvested = 100.0,
            listOf()
        )

        // Configuração do comportamento do mock
        runBlocking {
            `when`(mockService.getInvestments()).thenReturn(expectedInvestmentsResponse)
        }

        // Chamar o método que estamos testando
        val actualInvestmentsResponse = runBlocking {
            repository.getInvestments()
        }

        // Verificar se o resultado retornado é o esperado
        assertEquals(expectedInvestmentsResponse, actualInvestmentsResponse)
    }

    @Test(expected = Exception::class)
    fun `test getInvestments() throws Exception`() {
        // Mock do InvestmentsService
        val mockService = mock(InvestmentsService::class.java)

        // Instância do objeto a ser testado
        val repository = InvestmentsRepositoryImp(mockService)

        // Configuração do comportamento do mock para lançar uma exceção
        runBlocking {
            `when`(mockService.getInvestments()).thenThrow(Exception())
        }

        // Chamar o método que estamos testando, deve lançar uma exceção
        runBlocking {
            repository.getInvestments()
        }
    }
}
