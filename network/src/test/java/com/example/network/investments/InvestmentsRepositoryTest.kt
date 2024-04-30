package com.example.network.investments

import com.example.network.data.remote.repository.investments.InvestmentsRepository
import com.example.network.data.response.InvestmentsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class InvestmentsRepositoryTest {

    // Mock da resposta de investimentos
    private val investmentsResponse = InvestmentsResponse(
        10.0,
        listOf()
    )

    // Mock do repositório
    private val repository = mock(InvestmentsRepository::class.java)

    @Test
    fun `test getInvestments`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(repository.getInvestments()).thenReturn(investmentsResponse)

        // Chamar o método e obter o resultado
        val result = repository.getInvestments()

        // Verificar se o resultado é o esperado
        assertEquals(investmentsResponse, result)
    }
}
