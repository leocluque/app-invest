package com.example.stock_alert.use_case

import com.example.network.Resource
import com.example.network.data.remote.repository.stock.StockAlertRepository
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ExtractResponse
import com.example.network.data.response.ProductStatus
import com.example.network.data.response.ProductsResponse
import com.example.stock_alert.use_case.stock_alert.StockAlertUseCaseImp
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class StockAlertUseCaseImpTest {

    @Mock
    private lateinit var mockResponseBody: ResponseBody
    private lateinit var repository: StockAlertRepository
    private lateinit var useCase: StockAlertUseCaseImp
    private val response = listOf(ProductsResponse("", "", ProductStatus.AVAILABLE, 10.0))
    private val mockRequest = CreateProductAlertRequest("", "", ProductStatus.AVAILABLE, 10.0)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = mock()
        useCase = StockAlertUseCaseImp(repository)
    }

    @Test
    fun `test getAllAlerts success`() = runTest {
        whenever(repository.getAllAlerts()).thenReturn(response)

        // Act
        val result = useCase.getAllAlerts()

        // Assert
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
//
//    @Test
//    fun `testGetAllAlerts_HttpException`() = runBlocking {
//        val errorMessage = "HTTP 404 Response.error()"
//        val responseBody = errorMessage.toResponseBody("text/plain".toMediaTypeOrNull())
//        val response = Response.error<ExtractResponse>(404, responseBody)
//        val expectedException = HttpException(response)
//        Mockito.`when`(repository.getAllAlerts()).thenThrow(expectedException)
//
//        val flow = useCase.getAllAlerts()
//        val result = flow.last()
//
//        val actualMessage = (result as Resource.Error).message
//
//        assertEquals(actualMessage, expectedException.localizedMessage)
//    }




//    @Test
//    fun `test getAllAlerts error - HTTP exception`() = runTest {
//        val errorMessage = "HTTP 0 null"
//        `when`(repository.getAllAlerts()).thenAnswer {
//            throw HttpException(mock())
//        }
//
//        val result = useCase.getAllAlerts()
//
//        result.collect { resource ->
//            when (resource) {
//                is Resource.Loading -> {
//                    assertEquals(Resource.Loading, resource)
//                }
//
//                is Resource.Success -> {
//                    // Should not reach here
//                    assert(false)
//                }
//
//                is Resource.Error -> {
//                    assertEquals(errorMessage, resource.message)
//                }
//            }
//        }
//    }

    @Test
    fun `test getAllAlerts error - IO exception`() = runTest {
        val errorMessage =
            "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        `when`(repository.getAllAlerts()).thenAnswer {
            throw IOException(errorMessage)
        }
        val result = useCase.getAllAlerts()

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
    fun `test getAllAlerts empty response`() = runTest {
        `when`(repository.getAllAlerts()).thenAnswer {
            null
        }

        val result = useCase.getAllAlerts()

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


    @Test
    fun `test createAlert success`() = runTest {
        whenever(repository.createAlert(mockRequest)).thenReturn(mockResponseBody)

        // Act
        val result = useCase.createAlert(mockRequest)

        // Assert
        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }

                is Resource.Success -> {
                    assertEquals(mockResponseBody, resource.data)
                }

                is Resource.Error -> {
                    // Should not reach here
                    assert(false)
                }
            }
        }
    }

//    @Test
//    fun `test createAlert error - HTTP exception`() = runTest {
//        val errorMessage = "HTTP 0 null"
//        `when`(repository.createAlert(mockRequest)).thenAnswer {
//            throw HttpException(mock())
//        }
//
//        val result = useCase.createAlert(mockRequest)
//
//        result.collect { resource ->
//            when (resource) {
//                is Resource.Loading -> {
//                    assertEquals(Resource.Loading, resource)
//                }
//
//                is Resource.Success -> {
//                    // Should not reach here
//                    assert(false)
//                }
//
//                is Resource.Error -> {
//                    assertEquals(errorMessage, resource.message)
//                }
//            }
//        }
//    }

    @Test
    fun `test createAlert error - IO exception`() = runTest {
        val errorMessage =
            "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        `when`(repository.createAlert(mockRequest)).thenAnswer {
            throw IOException(errorMessage)
        }
        val result = useCase.createAlert(mockRequest)

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
    fun `test createAlert empty response`() = runTest {
        `when`(repository.createAlert(mockRequest)).thenAnswer {
            null
        }

        val result = useCase.createAlert(mockRequest)

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

    @Test
    fun `test updateAlert success`() = runTest {
        whenever(repository.updateAlert(mockRequest)).thenReturn(mockResponseBody)

        // Act
        val result = useCase.updateAlert(mockRequest)

        // Assert
        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }

                is Resource.Success -> {
                    assertEquals(mockResponseBody, resource.data)
                }

                is Resource.Error -> {
                    // Should not reach here
                    assert(false)
                }
            }
        }
    }

//    @Test
//    fun `test updateAlert error - HTTP exception`() = runTest {
//        val errorMessage = "HTTP 0 null"
//        `when`(repository.updateAlert(mockRequest)).thenAnswer {
//            throw HttpException(mock())
//        }
//
//        val result = useCase.updateAlert(mockRequest)
//
//        result.collect { resource ->
//            when (resource) {
//                is Resource.Loading -> {
//                    assertEquals(Resource.Loading, resource)
//                }
//
//                is Resource.Success -> {
//                    // Should not reach here
//                    assert(false)
//                }
//
//                is Resource.Error -> {
//                    assertEquals(errorMessage, resource.message)
//                }
//            }
//        }
//    }

    @Test
    fun `test updateAlert error - IO exception`() = runTest {
        val errorMessage =
            "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        `when`(repository.updateAlert(mockRequest)).thenAnswer {
            throw IOException(errorMessage)
        }
        val result = useCase.updateAlert(mockRequest)

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
    fun `test updateAlert empty response`() = runTest {
        `when`(repository.updateAlert(mockRequest)).thenAnswer {
            null
        }

        val result = useCase.updateAlert(mockRequest)

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

    @Test
    fun `test deleteAlert success`() = runTest {
        whenever(repository.deleteAlert(mockRequest)).thenReturn(mockResponseBody)

        // Act
        val result = useCase.deleteAlert(mockRequest)

        // Assert
        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    assertEquals(Resource.Loading, resource)
                }

                is Resource.Success -> {
                    assertEquals(mockResponseBody, resource.data)
                }

                is Resource.Error -> {
                    // Should not reach here
                    assert(false)
                }
            }
        }
    }

//    @Test
//    fun `test deleteAlert error - HTTP exception`() = runTest {
//        val errorMessage = "HTTP 0 null"
//        `when`(repository.deleteAlert(mockRequest)).thenAnswer {
//            throw HttpException(mock())
//        }
//
//        val result = useCase.deleteAlert(mockRequest)
//
//        result.collect { resource ->
//            when (resource) {
//                is Resource.Loading -> {
//                    assertEquals(Resource.Loading, resource)
//                }
//
//                is Resource.Success -> {
//                    // Should not reach here
//                    assert(false)
//                }
//
//                is Resource.Error -> {
//                    assertEquals(errorMessage, resource.message)
//                }
//            }
//        }
//    }

    @Test
    fun `test deleteAlert error - IO exception`() = runTest {
        val errorMessage =
            "Não foi possível acessar o servidor. Verifique sua conexão com a Internet"

        `when`(repository.deleteAlert(mockRequest)).thenAnswer {
            throw IOException(errorMessage)
        }
        val result = useCase.deleteAlert(mockRequest)

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
    fun `test deleteAlert empty response`() = runTest {
        `when`(repository.deleteAlert(mockRequest)).thenAnswer {
            null
        }

        val result = useCase.updateAlert(mockRequest)

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
