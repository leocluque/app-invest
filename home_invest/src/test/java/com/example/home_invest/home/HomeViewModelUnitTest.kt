package com.example.home_invest.home

import com.example.home_invest.ui.home.home.HomeViewModel
import com.example.home_invest.ui.home.home.UiEventHome
import com.example.home_invest.use_cases.balance.BalanceUseCase
import com.example.network.Resource
import com.example.network.data.response.BalanceResponse
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


class HomeViewModelTest {

    @MockK
    lateinit var balanceUseCase: BalanceUseCase

    private lateinit var viewModel: HomeViewModel
    private val testScheduler = TestCoroutineScheduler()
    private lateinit var launchedJob: Job


    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))

        balanceUseCase = mockk()
        viewModel = HomeViewModel(balanceUseCase)
    }

    @Test
    fun `getBalance_Success_EmitsLoadingTrueThenSuccess`() = runTest {
        val expectedBalance = 1000.00
        val expectedResponse = BalanceResponse(expectedBalance)
        coEvery { balanceUseCase.getBalance() } returns flow {
            emit(
                Resource.Success(
                    expectedResponse
                )
            )
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventHome.Loading -> assertTrue(event.isLoading)
                    is UiEventHome.Success -> assertEquals(expectedBalance, event.balance)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getBalance()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_Loading_EmitsLoadingTrue`() = runTest {
        coEvery { balanceUseCase.getBalance() } returns flow { emit(Resource.Loading) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventHome.Loading -> assertTrue(event.isLoading)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getBalance()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_Error_EmitsLoadingFalseThenError`() = runTest {
        val errorMessage = "Network Error"
        coEvery { balanceUseCase.getBalance() } returns flow { emit(Resource.Error(errorMessage)) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventHome.Loading -> assertFalse(event.isLoading)
                    is UiEventHome.Error -> assertEquals(errorMessage, event.error)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getBalance()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_MultipleEmissions_EmitsAllEvents`() = runTest {
        val expectedBalance = 1000.00
        val expectedResponse = BalanceResponse(expectedBalance)
        coEvery { balanceUseCase.getBalance() } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(expectedResponse))
        }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventHome.Loading -> assertTrue(event.isLoading)
                    is UiEventHome.Success -> assertEquals(expectedBalance, event.balance)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getBalance()
        launchedJob.cancel()
    }

    @Test
    fun `getBalance_CancelCollection_DoesNotCrash`() = runTest {
        coEvery { balanceUseCase.getBalance() } returns emptyFlow()
    }

    @Test
    fun `getBalance_ViewModelScopeException_EmitsError`() = runTest {
        val exception = RuntimeException("Unexpected error")
        coEvery { balanceUseCase.getBalance() } returns flow { emit(Resource.Error(exception.message.toString())) }

        launchedJob = launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventHome.Error -> assertEquals(exception.message, event.error)
                    else -> fail("Unexpected event: $event")
                }
            }
        }

        viewModel.getBalance()
        launchedJob.cancel()
    }
}