package com.example.home_invest.use_case.balance

import com.example.home_invest.use_cases.balance.BalanceUseCase
import com.example.home_invest.use_cases.balance.BalanceUseCaseImp
import com.example.network.Resource
import com.example.network.data.remote.repository.balance.BalanceRepository
import com.example.network.data.response.BalanceResponse
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.io.IOException

class BalanceUseCaseTest {

    @Mock
    lateinit var mockBalanceRepository: BalanceRepository

    lateinit var balanceUseCase: BalanceUseCase

    private val mockResponse = BalanceResponse(10.0)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        balanceUseCase = BalanceUseCaseImp(mockBalanceRepository)
    }

    @Test
    fun `getBalance should return loading state`() {
        runBlocking {
            `when`(mockBalanceRepository.getBalance()).thenReturn(mockResponse)

            val result = balanceUseCase.getBalance()

            result.collectIndexed { index, resource ->
                if (index == 0) {
                    when (resource) {
                        is Resource.Loading -> {
                            assertEquals(Resource.Loading, resource)
                        }

                        is Resource.Success -> {
                            assertEquals(null, resource.data)
                        }

                        is Resource.Error -> {
                            assertEquals(null, resource.message)
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `getBalance should return success state`() {
        runBlocking {
            `when`(mockBalanceRepository.getBalance()).thenReturn(mockResponse)

            val result = balanceUseCase.getBalance()

            result.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        assertFalse(false)
                    }

                    is Resource.Success -> {
                        assertEquals(mockResponse, resource.data)
                    }

                    is Resource.Error -> {
                        assertFalse(false)
                    }
                }
            }
        }
    }

    @Test
    fun `getBalance should return error state`() {
        val errorMessage =
            "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        runBlocking {
            // Simula um fluxo de recurso de erro
            `when`(mockBalanceRepository.getBalance()).thenAnswer {
                throw IOException(errorMessage)
            }

            val result = balanceUseCase.getBalance()

            result.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        assertFalse(false)
                    }

                    is Resource.Success -> {
                        assertFalse(false)
                    }

                    is Resource.Error -> {
                        assertEquals(errorMessage, resource.message)
                    }
                }
            }
        }
    }
}
