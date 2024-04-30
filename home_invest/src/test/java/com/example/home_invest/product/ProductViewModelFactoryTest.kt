package com.example.home_invest.product

import com.example.home_invest.ui.home.home.HomeViewModel
import com.example.home_invest.ui.home.product.ProductViewModel
import com.example.home_invest.ui.home.product.ProductViewModelFactory
import com.example.home_invest.use_cases.investments.InvestmentsUseCaseImp
import com.example.network.data.remote.repository.investments.InvestmentsRepository
import com.example.network.data.response.InvestmentsResponse
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ProductViewModelFactoryTest {

    private lateinit var repository: InvestmentsRepository
    private lateinit var factory: ProductViewModelFactory

    @Before
    fun setUp() {
        repository = mock(InvestmentsRepository::class.java)
        factory = ProductViewModelFactory(repository)
    }

    @Test
    fun testCreate() {
        val mockUseCase = mock(InvestmentsUseCaseImp::class.java)
        `when`(mockUseCase.getInvestments()).thenReturn(flow { InvestmentsResponse(10.0, listOf()) })

        val viewModel = factory.create(ProductViewModel::class.java)

        assertEquals(ProductViewModel::class.java, viewModel.javaClass)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testCreate_UnknownClass() {
        factory.create(HomeViewModel::class.java)
    }
}
