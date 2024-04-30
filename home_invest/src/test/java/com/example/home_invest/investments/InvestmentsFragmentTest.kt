package com.example.home_invest.investments

import android.view.LayoutInflater
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import com.example.home_invest.ui.home.investments.InvestmentsFragment
import com.example.home_invest.ui.home.investments.InvestmentsViewModel
import com.example.home_invest.ui.home.investments.InvestmentsViewModelFactory
import com.example.home_invest.ui.home.product.ProductViewModel
import com.example.home_invest.ui.home.product.ProductViewModelFactory
import com.example.home_invest.use_cases.extract.ExtractUseCase
import com.example.network.data.remote.repository.extract.ExtractRepository
import com.example.network.data.remote.repository.investments.InvestmentsRepository
import com.example.network.data.response.ContractedProducts
import com.example.network.data.response.ExtractResponse
import com.example.network.data.response.InvestmentsResponse
import com.example.network.data.response.TransactionType
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class InvestmentsFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var investmentsFragment: InvestmentsFragment
    private lateinit var viewModel: InvestmentsViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var mockFactory: ProductViewModelFactory
    private lateinit var mockExtractRepository: ExtractRepository
    private lateinit var mockInvestmentsRepository: InvestmentsRepository
    private lateinit var investmentsViewModelFactory: InvestmentsViewModelFactory
    private lateinit var extractUseCase: ExtractUseCase

    @Before
    fun setup() = runTest {
        extractUseCase = mock(ExtractUseCase::class.java)
        mockFactory = mock(ProductViewModelFactory::class.java)
        mockExtractRepository = mock(ExtractRepository::class.java)
        mockInvestmentsRepository = mock(InvestmentsRepository::class.java)
        investmentsViewModelFactory = InvestmentsViewModelFactory(mockExtractRepository)
        Mockito.`when`(mockFactory.create(ProductViewModel::class.java))
            .thenReturn(mock(ProductViewModel::class.java))
        Mockito.`when`(mockExtractRepository.getExtract()).thenReturn(emptyList())
        Mockito.`when`(mockInvestmentsRepository.getInvestments())
            .thenReturn(InvestmentsResponse(10.0, listOf()))

        investmentsFragment = InvestmentsFragment()
        investmentsFragment.viewLifecycleOwnerLiveData.observeForever { lifecycleOwner ->
            if (lifecycleOwner != null) {
                viewModel = investmentsViewModelFactory.create(InvestmentsViewModel::class.java)
                productViewModel = mockFactory.create(ProductViewModel::class.java)
                investmentsFragment.viewModel = viewModel
                investmentsFragment.productViewModel = productViewModel
            }
        }
        val scenario = launchFragmentInContainer<InvestmentsFragment>()
        scenario.onFragment { fragment ->
            investmentsFragment = fragment
        }
    }

    @Test
    fun `test onCreateView`() {
        assertNotNull(
            investmentsFragment.onCreateView(
                LayoutInflater.from(ApplicationProvider.getApplicationContext()),
                null,
                null
            )
        )
    }

    @Test
    fun `test onViewCreated`() {
        investmentsFragment.productViewModel = mockFactory.create(ProductViewModel::class.java)
        investmentsFragment.onViewCreated(mock(), null)
        assertNotNull(investmentsFragment.productViewModel)
    }

    @Test
    fun `test setAdapter`() {
        val list = listOf(
            ContractedProducts("", 10.0, 10.0, 10.0, ""),
            ContractedProducts("", 10.0, 10.0, 10.0, ""),
        )
        investmentsFragment.setAdapter(list)
        assertNotNull(investmentsFragment.adapter)
    }

    @Test
    fun `test setExtractAdapter`() {
        val list = listOf(
            ExtractResponse("", "", 10.0, TransactionType.EXPENSE, 10.0),
            ExtractResponse("", "", 10.0, TransactionType.EXPENSE, 10.0),
        )
        investmentsFragment.setExtractAdapter(list)
        assertNotNull(investmentsFragment.extractAdapter)
    }
}
