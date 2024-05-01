package com.example.stock_alert

import com.example.network.Resource
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductStatus
import com.example.network.data.response.ProductsResponse
import com.example.stock_alert.ui.stock_alert.StockAlertViewModel
import com.example.stock_alert.ui.stock_alert.UiEventStock
import com.example.stock_alert.use_case.stock_alert.StockAlertUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test

class StockAlertViewModelTest {

    @MockK
    lateinit var useCase: StockAlertUseCase

    @MockK
    lateinit var resposeBody: ResponseBody

    private lateinit var viewModel: StockAlertViewModel
    private val testScheduler = TestCoroutineScheduler()
    private lateinit var launchedJob: Job
    private val mockRequest = CreateProductAlertRequest("", "", ProductStatus.AVAILABLE, 10.0)


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))

        useCase = mockk()
        viewModel = StockAlertViewModel(useCase)
    }

    @Test
    fun `getAllAlerts_Success_EmitsLoadingTrueThenSuccess`() = runTest {
        val expectedResponse = listOf(ProductsResponse("", "", ProductStatus.AVAILABLE, 10.0))
        coEvery { useCase.getAllAlerts() } returns flow {
            emit(
                Resource.Success(
                    expectedResponse
                )
            )
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventStock.Success -> TestCase.assertEquals(
                        expectedResponse,
                        event.products
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getAllAlerts()
        launchedJob.cancel()
    }

    @Test
    fun `getAllAlerts_Loading_EmitsLoadingTrue`() = runTest {
        coEvery { useCase.getAllAlerts() } returns flow { emit(Resource.Loading) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getAllAlerts()
        launchedJob.cancel()
    }

    @Test
    fun `getAllAlerts_Error_EmitsLoadingFalseThenError`() = runTest {
        val errorMessage = "Network Error"
        coEvery { useCase.getAllAlerts() } returns flow { emit(Resource.Error(errorMessage)) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertFalse(event.isLoading)
                    is UiEventStock.Error -> TestCase.assertEquals(errorMessage, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getAllAlerts()
        launchedJob.cancel()
    }

    @Test
    fun `getAllAlerts_MultipleEmissions_EmitsAllEvents`() = runTest {
        val expectedBalance = 1000.00
        val expectedResponse = listOf(ProductsResponse("", "", ProductStatus.AVAILABLE, 10.0))
        coEvery { useCase.getAllAlerts() } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(expectedResponse))
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventStock.Success -> TestCase.assertEquals(
                        expectedBalance,
                        event.products
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getAllAlerts()
        launchedJob.cancel()
    }

    @Test
    fun `getAllAlerts_CancelCollection_DoesNotCrash`() = runTest {
        coEvery { useCase.getAllAlerts() } returns emptyFlow()
    }

    @Test
    fun `getAllAlerts_ViewModelScopeException_EmitsError`() = runTest {
        val exception = RuntimeException("Unexpected error")
        coEvery { useCase.getAllAlerts() } returns flow { emit(Resource.Error(exception.message.toString())) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Error -> TestCase.assertEquals(exception.message, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getAllAlerts()
        launchedJob.cancel()
    }


    @Test
    fun `createAlert_Success_EmitsLoadingTrueThenSuccess`() = runTest {
        val expectedResponse = (resposeBody)
        coEvery { useCase.createAlert(mockRequest) } returns flow {
            emit(
                Resource.Success(
                    expectedResponse
                )
            )
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventStock.Success -> TestCase.assertEquals(
                        expectedResponse,
                        event.products
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.createAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `createAlert_Loading_EmitsLoadingTrue`() = runTest {
        coEvery { useCase.createAlert(mockRequest) } returns flow { emit(Resource.Loading) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.createAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `createAlert_Error_EmitsLoadingFalseThenError`() = runTest {
        val errorMessage = "Network Error"
        coEvery { useCase.createAlert(mockRequest) } returns flow { emit(Resource.Error(errorMessage)) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertFalse(event.isLoading)
                    is UiEventStock.Error -> TestCase.assertEquals(errorMessage, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.createAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `createAlert_MultipleEmissions_EmitsAllEvents`() = runTest {
        val expectedBalance = 1000.00
        val expectedResponse = resposeBody
        coEvery { useCase.createAlert(mockRequest) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(expectedResponse))
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventStock.Success -> TestCase.assertEquals(
                        expectedBalance,
                        event.products
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.createAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `createAlert_CancelCollection_DoesNotCrash`() = runTest {
        coEvery { useCase.createAlert(mockRequest) } returns emptyFlow()
    }

    @Test
    fun `createAlert_ViewModelScopeException_EmitsError`() = runTest {
        val exception = RuntimeException("Unexpected error")
        coEvery { useCase.createAlert(mockRequest) } returns flow { emit(Resource.Error(exception.message.toString())) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Error -> TestCase.assertEquals(exception.message, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.createAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `updateAlert_Success_EmitsLoadingTrueThenSuccess`() = runTest {
        val expectedResponse = (resposeBody)
        coEvery { useCase.updateAlert(mockRequest) } returns flow {
            emit(
                Resource.Success(
                    expectedResponse
                )
            )
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventStock.Success -> TestCase.assertEquals(
                        expectedResponse,
                        event.products
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.updateAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `updateAlert_Loading_EmitsLoadingTrue`() = runTest {
        coEvery { useCase.updateAlert(mockRequest) } returns flow { emit(Resource.Loading) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.updateAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `updateAlert_Error_EmitsLoadingFalseThenError`() = runTest {
        val errorMessage = "Network Error"
        coEvery { useCase.updateAlert(mockRequest) } returns flow { emit(Resource.Error(errorMessage)) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertFalse(event.isLoading)
                    is UiEventStock.Error -> TestCase.assertEquals(errorMessage, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.updateAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `updateAlert_MultipleEmissions_EmitsAllEvents`() = runTest {
        val expectedBalance = 1000.00
        val expectedResponse = resposeBody
        coEvery { useCase.updateAlert(mockRequest) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(expectedResponse))
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventStock.Success -> TestCase.assertEquals(
                        expectedBalance,
                        event.products
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.updateAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `updateAlert_CancelCollection_DoesNotCrash`() = runTest {
        coEvery { useCase.updateAlert(mockRequest) } returns emptyFlow()
    }

    @Test
    fun `updateAlert_ViewModelScopeException_EmitsError`() = runTest {
        val exception = RuntimeException("Unexpected error")
        coEvery { useCase.updateAlert(mockRequest) } returns flow { emit(Resource.Error(exception.message.toString())) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Error -> TestCase.assertEquals(exception.message, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.updateAlert(mockRequest)
        launchedJob.cancel()
    }

    // TODO

    @Test
    fun `deleteAlert_Success_EmitsLoadingTrueThenSuccess`() = runTest {
        val expectedResponse = (resposeBody)
        coEvery { useCase.deleteAlert(mockRequest) } returns flow {
            emit(
                Resource.Success(
                    expectedResponse
                )
            )
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventStock.Success -> TestCase.assertEquals(
                        expectedResponse,
                        event.products
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.deleteAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `deleteAlert_Loading_EmitsLoadingTrue`() = runTest {
        coEvery { useCase.deleteAlert(mockRequest) } returns flow { emit(Resource.Loading) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.deleteAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `deleteAlert_Error_EmitsLoadingFalseThenError`() = runTest {
        val errorMessage = "Network Error"
        coEvery { useCase.deleteAlert(mockRequest) } returns flow { emit(Resource.Error(errorMessage)) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertFalse(event.isLoading)
                    is UiEventStock.Error -> TestCase.assertEquals(errorMessage, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.deleteAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `deleteAlert_MultipleEmissions_EmitsAllEvents`() = runTest {
        val expectedBalance = 1000.00
        val expectedResponse = resposeBody
        coEvery { useCase.deleteAlert(mockRequest) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(expectedResponse))
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventStock.Success -> TestCase.assertEquals(
                        expectedBalance,
                        event.products
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.deleteAlert(mockRequest)
        launchedJob.cancel()
    }

    @Test
    fun `deleteAlert_CancelCollection_DoesNotCrash`() = runTest {
        coEvery { useCase.deleteAlert(mockRequest) } returns emptyFlow()
    }

    @Test
    fun `deleteAlert_ViewModelScopeException_EmitsError`() = runTest {
        val exception = RuntimeException("Unexpected error")
        coEvery { useCase.deleteAlert(mockRequest) } returns flow { emit(Resource.Error(exception.message.toString())) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventStock.Error -> TestCase.assertEquals(exception.message, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.deleteAlert(mockRequest)
        launchedJob.cancel()
    }
}