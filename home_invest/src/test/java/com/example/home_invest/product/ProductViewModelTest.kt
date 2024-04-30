package com.example.home_invest.product

import com.example.home_invest.ui.home.investments.UiEventInvestments
import com.example.home_invest.ui.home.product.ProductViewModel
import com.example.home_invest.use_cases.investments.InvestmentsUseCase
import com.example.network.Resource
import com.example.network.data.response.InvestmentsResponse
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
import org.junit.Before
import org.junit.Test

class ProductViewModelTest {

    @MockK
    lateinit var useCase: InvestmentsUseCase

    private lateinit var viewModel: ProductViewModel
    private val testScheduler = TestCoroutineScheduler()
    private lateinit var launchedJob: Job


    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))

        useCase = mockk()
        viewModel = ProductViewModel(useCase)
    }

    @Test
    fun `getBalance_Success_EmitsLoadingTrueThenSuccess`() = runTest {
        val mockData = InvestmentsResponse(10.0, listOf())
        val successResult = Resource.Success(mockData)

        coEvery { useCase.getInvestments() } returns flow { emit(successResult) }

        launchedJob = launch {
            viewModel.uiEventInvestments.collectLatest { event ->
                when (event) {
                    is UiEventInvestments.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventInvestments.Success -> TestCase.assertEquals(
                        successResult,
                        event.investments
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getInvestments()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_Loading_EmitsLoadingTrue`() = runTest {
        coEvery { useCase.getInvestments() } returns flow { emit(Resource.Loading) }

        launchedJob = launch {
            viewModel.uiEventInvestments.collectLatest { event ->
                when (event) {
                    is UiEventInvestments.Loading -> TestCase.assertTrue(event.isLoading)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getInvestments()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_Error_EmitsLoadingFalseThenError`() = runTest {
        val errorMessage = "Network Error"
        coEvery { useCase.getInvestments() } returns flow { emit(Resource.Error(errorMessage)) }

        launchedJob = launch {
            viewModel.uiEventInvestments.collectLatest { event ->
                when (event) {
                    is UiEventInvestments.Loading -> TestCase.assertFalse(event.isLoading)
                    is UiEventInvestments.Error -> TestCase.assertEquals(errorMessage, event.error)
                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getInvestments()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_MultipleEmissions_EmitsAllEvents`() = runTest {
        val mockData = InvestmentsResponse(10.0, listOf())

        coEvery { useCase.getInvestments() } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(mockData))
        }

        launchedJob = launch {
            viewModel.uiEventInvestments.collectLatest { event ->
                when (event) {
                    is UiEventInvestments.Loading -> TestCase.assertTrue(event.isLoading)
                    is UiEventInvestments.Success -> TestCase.assertEquals(
                        mockData,
                        event.investments
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getInvestments()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_CancelCollection_DoesNotCrash`() = runTest {
        coEvery { useCase.getInvestments() } returns emptyFlow()
    }

    @Test
    fun `getBalance_ViewModelScopeException_EmitsError`() = runTest {
        val exception = RuntimeException("Unexpected error")
        coEvery { useCase.getInvestments() } returns flow { emit(Resource.Error(exception.message.toString())) }

        launchedJob = launch {
            viewModel.uiEventInvestments.collectLatest { event ->
                when (event) {
                    is UiEventInvestments.Error -> TestCase.assertEquals(
                        exception.message,
                        event.error
                    )

                    else -> TestCase.fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getInvestments()
        launchedJob.cancel()
    }
}
