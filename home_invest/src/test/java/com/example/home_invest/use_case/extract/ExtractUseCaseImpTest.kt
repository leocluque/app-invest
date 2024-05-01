package com.example.home_invest.use_case.extract

import com.example.home_invest.use_cases.extract.ExtractUseCaseImp
import com.example.network.Resource
import com.example.network.data.remote.repository.extract.ExtractRepository
import com.example.network.data.response.ExtractResponse
import com.example.network.data.response.TransactionType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import java.io.IOException

class ExtractUseCaseImpTest {

    private lateinit var repository: ExtractRepository
    private lateinit var useCase: ExtractUseCaseImp
    private val response = listOf(ExtractResponse("", "", 10.0, TransactionType.EXPENSE, 10.0))

    @Before
    fun setUp() {
        repository = mock()
        useCase = ExtractUseCaseImp(repository)
    }

    @Test
    fun `test getExtract success`() = runTest {

        whenever(repository.getExtract()).thenReturn(response)

        val result = useCase.getExtract()

        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }

                is Resource.Success -> {
                    assertEquals(response, resource.data)
                }

                is Resource.Error -> {
                    // Should not reach here
                    assert(false)
                }
            }
        }
    }

    @Test
    fun `test getExtract loading`() = runTest {

        whenever(repository.getExtract()).thenReturn(response)

        val result = useCase.getExtract()

        result.collectIndexed { index, resource ->
            if (index == 0) {
                when (resource) {
                    is Resource.Loading -> {
                        assertEquals(Resource.Loading, resource)
                    }

                    is Resource.Success -> {
                        assert(false)
                    }

                    is Resource.Error -> {
                        // Should not reach here
                        assert(false)
                    }
                }
            }
        }
    }

    @Test
    fun `test getExtract error - IO exception`() = runTest {
        val errorMessage =
            "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        `when`(repository.getExtract()).thenAnswer {
            throw IOException(errorMessage)
        }
        val result = useCase.getExtract()

        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }

                is Resource.Success -> {
                    assert(false)
                }

                is Resource.Error -> {
                    assertEquals(errorMessage, resource.message)
                }
            }
        }
    }
}
