package com.example.home_invest.use_case.investments

import com.example.home_invest.use_cases.investments.InvestmentsUseCaseImp
import com.example.network.Resource
import com.example.network.data.remote.repository.investments.InvestmentsRepository
import com.example.network.data.response.InvestmentsResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import java.io.IOException

class InvestmentsUseCaseImpTest {

    private lateinit var repository: InvestmentsRepository
    private lateinit var useCase: InvestmentsUseCaseImp
    private val mockResponse = InvestmentsResponse(10.0, listOf())

    @Before
    fun setUp() {
        repository = mock()
        useCase = InvestmentsUseCaseImp(repository)
    }

    @Test
    fun `test getBalance success`() = runTest {
        // Arrange
        val flow = flow {
            emit(Resource.Loading)
            emit(Resource.Success(mockResponse))
        }
        whenever(repository.getInvestments()).thenReturn(mockResponse)

        // Act
        val result = useCase.getInvestments()

        // Assert
        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }
                is Resource.Success -> {
                    assertEquals(mockResponse, resource.data)
                }
                is Resource.Error -> {
                    // Should not reach here
                    assert(false)
                }
            }
        }
    }

//    @Test
//    fun `test getBalance error - HTTP exception`() = runTest {
//        // Arrange
//        val errorMessage = "HTTP 0 null"
//        `when`(repository.getInvestments()).thenAnswer {
//            throw HttpException(mock())
//        }
//
//        // Act
//        val result = useCase.getInvestments()
//
//        // Assert
//        result.collect { resource ->
//            when (resource) {
//                is Resource.Loading -> {
//                    assertEquals(Resource.Loading, resource)
//                }
//                is Resource.Success -> {
//                    // Should not reach here
//                    assert(false)
//                }
//                is Resource.Error -> {
//                    assertEquals(errorMessage, resource.message)
//                }
//            }
//        }
//    }

    @Test
    fun `test getBalance error - IO exception`() = runTest {
        // Arrange
        val errorMessage = "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        `when`(repository.getInvestments()).thenAnswer {
            throw IOException(errorMessage)
        }
        // Act
        val result = useCase.getInvestments()

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
