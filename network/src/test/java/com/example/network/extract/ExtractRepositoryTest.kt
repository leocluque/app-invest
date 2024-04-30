package com.example.network.extract

import com.example.network.data.remote.repository.extract.ExtractRepository
import com.example.network.data.response.ExtractResponse
import com.example.network.data.response.TransactionType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ExtractRepositoryTest {

    // Mock da lista de respostas do extrato
    private val extractResponseList = listOf(
        ExtractResponse(name = "", "", 0.0, TransactionType.EXPENSE, 10.0),
        ExtractResponse(name = "", "", 0.0, TransactionType.EXPENSE, 10.0),
        // Adicione mais objetos ExtractResponse conforme necessário para seus testes
    )

    // Mock do repositório
    private val repository = mock(ExtractRepository::class.java)

    @Test
    fun `test getExtract`() = runBlocking {
        // Configurar o comportamento do mock
        `when`(repository.getExtract()).thenReturn(extractResponseList)

        // Chamar o método e obter o resultado
        val result = repository.getExtract()

        // Verificar se o resultado é o esperado
        assertEquals(extractResponseList, result)
    }
}
