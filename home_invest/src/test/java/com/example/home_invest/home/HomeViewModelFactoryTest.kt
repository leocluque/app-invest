package com.example.home_invest.home

import com.example.home_invest.ui.home.home.HomeViewModel
import com.example.home_invest.ui.home.home.HomeViewModelFactory
import com.example.home_invest.ui.home.product.ProductViewModel
import com.example.home_invest.use_cases.balance.BalanceUseCaseImp
import com.example.network.data.remote.repository.balance.BalanceRepository
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelFactoryTest {

    @Mock
    private lateinit var mockRepository: BalanceRepository

    private lateinit var factory: HomeViewModelFactory

    @Before
    fun setUp() {
        factory = HomeViewModelFactory(mockRepository)
    }

    @Test
    fun `test create - success`() {
        // Arrange
        val expectedViewModel = HomeViewModel(BalanceUseCaseImp(mockRepository))

        // Act
        val viewModel = factory.create(HomeViewModel::class.java)

        // Assert
        assertEquals(
            expectedViewModel.toString().split("@").first(),
            viewModel.toString().split("@").first()
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test create - unknown class`() {
        // Act
        factory.create(ProductViewModel::class.java)
    }
}
