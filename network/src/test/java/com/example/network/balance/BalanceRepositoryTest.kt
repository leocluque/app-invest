package com.example.network.balance

import com.example.network.data.remote.repository.balance.BalanceRepository
import com.example.network.data.response.BalanceResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class BalanceRepositoryTest {

    // Mock da resposta do balance
    private val balanceResponse = BalanceResponse(
        balance = 10.0
    )

    // Mock do repositório
    private val repository = mock(BalanceRepository::class.java)

    @Test
    fun `test getBalance`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(repository.getBalance()).thenReturn(balanceResponse)

        // Chamar o método e obter o resultado
        val result = repository.getBalance()

        // Verificar se o resultado é o esperado
        assertEquals(balanceResponse, result)
    }
}
