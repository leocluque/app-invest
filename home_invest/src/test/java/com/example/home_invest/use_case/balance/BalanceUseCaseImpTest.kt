package com.example.home_invest.use_case.balance

import com.example.home_invest.use_cases.balance.BalanceUseCaseImp
import com.example.network.Resource
import com.example.network.data.remote.repository.balance.BalanceRepository
import com.example.network.data.response.BalanceResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import java.io.IOException

class BalanceUseCaseImpTest {

    private lateinit var repository: BalanceRepository
    private lateinit var useCase: BalanceUseCaseImp
    private val balanceResponse = BalanceResponse(10.0)

    @Before
    fun setUp() {
        repository = mock()
        useCase = BalanceUseCaseImp(repository)
    }

    @Test
    fun `test getBalance success`() = runTest {
        whenever(repository.getBalance()).thenReturn(balanceResponse)

        // Act
        val result = useCase.getBalance()

        // Assert
        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }

                is Resource.Success -> {
                    assertEquals(balanceResponse, resource.data)
                }

                is Resource.Error -> {
                    // Should not reach here
                    assert(false)
                }
            }
        }
    }

    @Test
    fun `test getBalance error - IO exception`() = runTest {
        val errorMessage =
            "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        `when`(repository.getBalance()).thenAnswer {
            throw IOException(errorMessage)
        }
        val result = useCase.getBalance()

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

    @Test
    fun `test getBalance empty response`() = runTest {
        `when`(repository.getBalance()).thenAnswer {
            null
        }

        val result = useCase.getBalance()

        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }

                is Resource.Success -> {
                    // Should not reach here
                    assertEquals(null, resource.data)
                }

                is Resource.Error -> {
                    assertEquals("Empty response", resource.message)
                }
            }
        }
    }
}
