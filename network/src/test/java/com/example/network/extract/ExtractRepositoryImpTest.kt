package com.example.network.extract

import com.example.network.data.remote.repository.extract.ExtractRepositoryImp
import com.example.network.data.remote.service.ExtractService
import com.example.network.data.response.ExtractResponse
import com.example.network.data.response.TransactionType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ExtractRepositoryImpTest {

    @Test
    fun `test getExtract() success`() {
        // Mock do ExtractService
        val mockService = mock(ExtractService::class.java)

        // Instância do objeto a ser testado
        val repository = ExtractRepositoryImp(mockService)

        // Resposta esperada do serviço
        val expectedExtractResponse = listOf(
            ExtractResponse(
                name = "Produto 1",
                "10/10/10",
                100.0,
                TransactionType.EXPENSE,
                100.0
            )
        )

        // Configuração do comportamento do mock
        runBlocking {
            `when`(mockService.getExtract()).thenReturn(expectedExtractResponse)
        }

        // Chamar o método que estamos testando
        val actualExtractResponse = runBlocking {
            repository.getExtract()
        }

        // Verificar se o resultado retornado é o esperado
        assertEquals(expectedExtractResponse, actualExtractResponse)
    }

    @Test(expected = Exception::class)
    fun `test getExtract() throws Exception`() {
        // Mock do ExtractService
        val mockService = mock(ExtractService::class.java)

        // Instância do objeto a ser testado
        val repository = ExtractRepositoryImp(mockService)

        // Configuração do comportamento do mock para lançar uma exceção
        runBlocking {
            `when`(mockService.getExtract()).thenThrow(Exception())
        }

        // Chamar o método que estamos testando, deve lançar uma exceção
        runBlocking {
            repository.getExtract()
        }
    }
}
