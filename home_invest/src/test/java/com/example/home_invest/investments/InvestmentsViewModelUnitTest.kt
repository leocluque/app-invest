package com.example.home_invest.investments

import com.example.home_invest.ui.home.investments.InvestmentsViewModel
import com.example.home_invest.ui.home.investments.UiEventExtract
import com.example.home_invest.use_cases.extract.ExtractUseCase
import com.example.network.Resource
import com.example.network.data.response.ExtractResponse
import com.example.network.data.response.TransactionType
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
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


class InvestmentsViewModelUnitTest {

    @MockK
    lateinit var extractUseCase: ExtractUseCase

    private lateinit var viewModel: InvestmentsViewModel
    private val testScheduler = TestCoroutineScheduler()
    private lateinit var launchedJob: Job


    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        extractUseCase = mockk()
        viewModel = InvestmentsViewModel(extractUseCase)
    }

    @Test
    fun `getExtract_Success_EmitsLoadingTrueThenSuccess`() = runTest {
        val expectedBalance = 1000.00
        val expectedResponse = listOf(ExtractResponse("", "", 10.0, TransactionType.EXPENSE, 10.0))
        coEvery { extractUseCase.getExtract() } returns flow {
            emit(
                Resource.Success(
                    expectedResponse
                )
            )
        }

        launchedJob = launch {
            viewModel.uiEventExtract.collectLatest { event ->
                when (event) {
                    is UiEventExtract.Loading -> assertTrue(event.isLoading)
                    is UiEventExtract.Success -> assertEquals(expectedBalance, event.extract)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getExtract()
        launchedJob.cancel()
    }

    @Test
    fun `getExtract_Error_EmitsErrorThenSuccess`() = runTest {
        coEvery { extractUseCase.getExtract() } returns flow {
            emit(
                Resource.Error(
                    "Error"
                )
            )
        }
        launchedJob = launch {
            viewModel.uiEventExtract.collectLatest { event ->
                when (event) {
                    is UiEventExtract.Loading -> assert(false)
                    is UiEventExtract.Success ->assert(false)
                    is UiEventExtract.Error -> assertEquals("Error", event.error)
                }
            }
        }

        viewModel.getExtract()
        launchedJob.cancel()
    }

    @Test
    fun `getExtract_Loading_EmitsLoadingTrue`() = runTest {
        coEvery { extractUseCase.getExtract() } returns flow { emit(Resource.Loading) }

        launchedJob = launch {
            viewModel.uiEventExtract.collectLatest { event ->
                when (event) {
                    is UiEventExtract.Loading -> assertTrue(event.isLoading)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getExtract()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_Error_EmitsLoadingFalseThenError`() = runTest {
        val errorMessage = "Network Error"
        coEvery { extractUseCase.getExtract() } returns flow { emit(Resource.Error(errorMessage)) }

        launchedJob = launch {
            viewModel.uiEventExtract.collectLatest { event ->
                when (event) {
                    is UiEventExtract.Loading -> assertFalse(event.isLoading)
                    is UiEventExtract.Error -> assertEquals(errorMessage, event.error)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getExtract()
        launchedJob.cancel()
    }

    @Test
    fun `getExtract_MultipleEmissions_EmitsAllEvents`() = runTest {
        val expectedResponse = listOf(ExtractResponse("", "", 10.0, TransactionType.EXPENSE, 10.0))
        coEvery { extractUseCase.getExtract() } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(expectedResponse))
        }

        launchedJob = launch {
            viewModel.uiEventExtract.collectLatest { event ->
                when (event) {
                    is UiEventExtract.Loading -> assertTrue(event.isLoading)
                    is UiEventExtract.Success -> assertEquals(expectedResponse, event.extract)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getExtract()
        launchedJob.cancel()
    }

    @Test
    fun `getExtract_CancelCollection_DoesNotCrash`() = runTest {
        coEvery { extractUseCase.getExtract() } returns emptyFlow()
    }

    @Test
    fun `getExtract_ViewModelScopeException_EmitsError`() = runTest {
        val exception = RuntimeException("Unexpected error")
        coEvery { extractUseCase.getExtract() } returns flow { emit(Resource.Error(exception.message.toString())) }

        launchedJob = launch {
            viewModel.uiEventExtract.collectLatest { event ->
                when (event) {
                    is UiEventExtract.Error -> assertEquals(exception.message, event.error)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getExtract()
        launchedJob.cancel()
    }
}