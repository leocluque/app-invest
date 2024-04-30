package com.example.network.service

import com.example.network.data.remote.service.ExtractService
import com.example.network.data.response.ExtractResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ExtractServiceTest {

    // Mock da lista de respostas de extrato
    private val extractList = emptyList<ExtractResponse>()

    // Mock do serviço
    private val service = mock(ExtractService::class.java)

    @Test
    fun `test getExtract`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(service.getExtract()).thenReturn(extractList)

        // Chamar o método e obter o resultado
        val result = service.getExtract()

        // Verificar se o resultado é o esperado
        assertEquals(extractList, result)
    }
}
