package com.example.home_invest.use_case.balance

import com.example.home_invest.use_cases.balance.BalanceUseCaseImp
import com.example.network.Resource
import com.example.network.data.remote.repository.balance.BalanceRepository
import com.example.network.data.response.BalanceResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import java.io.IOException

class BalanceUseCaseImpTest {

    private lateinit var repository: BalanceRepository
    private lateinit var useCase: BalanceUseCaseImp
    val balanceResponse = BalanceResponse(10.0)

    @Before
    fun setUp() {
        repository = mock()
        useCase = BalanceUseCaseImp(repository)
    }

    @Test
    fun `test getBalance success`() = runBlockingTest {
        // Arrange
        val flow = flow {
            emit(Resource.Loading)
            emit(Resource.Success(balanceResponse))
        }
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
    fun `test getBalance error - HTTP exception`() = runTest {
        // Arrange
        val errorMessage = "HTTP 0 null"
        `when`(repository.getBalance()).thenAnswer {
            throw HttpException(mock())
        }

        // Act
        val result = useCase.getBalance()

        // Assert
        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }
                is Resource.Success -> {
                    // Should not reach here
                    assert(false)
                }
                is Resource.Error -> {
                    assertEquals(errorMessage, resource.message)
                }
            }
        }
    }

    @Test
    fun `test getBalance error - IO exception`() = runTest {
        // Arrange
        val errorMessage = "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        `when`(repository.getBalance()).thenAnswer {
            throw IOException(errorMessage)
        }
        // Act
        val result = useCase.getBalance()

        // Assert
        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }
                is Resource.Success -> {
                    // Should not reach here
                    assert(false)
                }
                is Resource.Error -> {
                    assertEquals(errorMessage, resource.message)
                }
            }
        }
    }
}
