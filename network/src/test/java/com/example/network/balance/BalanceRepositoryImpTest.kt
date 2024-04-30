package com.example.network.balance

import com.example.network.data.remote.repository.balance.BalanceRepositoryImp
import com.example.network.data.remote.service.BalanceService
import com.example.network.data.response.BalanceResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class BalanceRepositoryImpTest {

    @Test
    fun `test getBalance() success`() {
        // Mock do BalanceService
        val mockService = mock(BalanceService::class.java)

        // Instância do objeto a ser testado
        val repository = BalanceRepositoryImp(mockService)

        // Resposta esperada do serviço
        val expectedBalanceResponse = BalanceResponse(balance = 100.0)

        // Configuração do comportamento do mock
        runBlocking {
            `when`(mockService.getBalance()).thenReturn(expectedBalanceResponse)
        }

        // Chamar o método que estamos testando
        val actualBalanceResponse = runBlocking {
            repository.getBalance()
        }

        // Verificar se o resultado retornado é o esperado
        assertEquals(expectedBalanceResponse, actualBalanceResponse)
    }

    @Test(expected = Exception::class)
    fun `test getBalance() throws Exception`() {
        // Mock do BalanceService
        val mockService = mock(BalanceService::class.java)

        // Instância do objeto a ser testado
        val repository = BalanceRepositoryImp(mockService)

        // Configuração do comportamento do mock para lançar uma exceção
        runBlocking {
            `when`(mockService.getBalance()).thenThrow(Exception())
        }

        // Chamar o método que estamos testando, deve lançar uma exceção
        runBlocking {
            repository.getBalance()
        }
    }
}
