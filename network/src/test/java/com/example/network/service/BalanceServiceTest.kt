package com.example.network.service

import com.example.network.data.remote.service.BalanceService
import com.example.network.data.response.BalanceResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class BalanceServiceTest {

    // Mock da resposta do saldo
    private val balanceResponse = BalanceResponse(balance = 10.0)

    // Mock do serviço
    private val service = mock(BalanceService::class.java)

    @Test
    fun `test getBalance`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(service.getBalance()).thenReturn(balanceResponse)

        // Chamar o método e obter o resultado
        val result = service.getBalance()

        // Verificar se o resultado é o esperado
        assertEquals(balanceResponse, result)
    }
}
