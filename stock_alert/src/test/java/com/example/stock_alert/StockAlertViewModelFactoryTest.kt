package com.example.stock_alert

import androidx.lifecycle.ViewModel
import com.example.network.data.remote.repository.stock.StockAlertRepository
import com.example.stock_alert.ui.stock_alert.StockAlertViewModel
import com.example.stock_alert.ui.stock_alert.StockAlertViewModelFactory
import com.example.stock_alert.use_case.stock_alert.StockAlertUseCaseImp
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelFactoryTest {

    @Mock
    private lateinit var repository: StockAlertRepository

    private lateinit var factory: StockAlertViewModelFactory

    @Before
    fun setUp() {
        factory = StockAlertViewModelFactory(repository)
    }

    @Test
    fun `test create - success`() {
        // Arrange
        val expectedViewModel = StockAlertViewModel(StockAlertUseCaseImp(repository))

        // Act
        val viewModel = factory.create(StockAlertViewModel::class.java)

        // Assert
        TestCase.assertEquals(
            expectedViewModel.toString().split("@").first(),
            viewModel.toString().split("@").first()
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test create - unknown class`() {
        // Act
        factory.create(TestViewModel::class.java)
    }
}

class TestViewModel : ViewModel() {

}