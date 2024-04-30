package com.example.network.service

import com.example.network.data.remote.service.InvestmentsService
import com.example.network.data.response.InvestmentsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class InvestmentsServiceTest {

    // Mock da resposta de investimentos
    private val investmentsResponse = InvestmentsResponse(totalInvested = 0.0, listOf())

    // Mock do serviço
    private val service = mock(InvestmentsService::class.java)

    @Test
    fun `test getInvestments`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(service.getInvestments()).thenReturn(investmentsResponse)

        // Chamar o método e obter o resultado
        val result = service.getInvestments()

        // Verificar se o resultado é o esperado
        assertEquals(investmentsResponse, result)
    }
}
