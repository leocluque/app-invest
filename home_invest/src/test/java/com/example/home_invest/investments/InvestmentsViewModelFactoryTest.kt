package com.example.home_invest.investments

import com.example.home_invest.ui.home.investments.InvestmentsViewModel
import com.example.home_invest.ui.home.investments.InvestmentsViewModelFactory
import com.example.network.data.remote.repository.extract.ExtractRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class InvestmentsViewModelFactoryTest {

    @Test
    fun `test create - verify InvestmentsViewModel instance`() {
        val mockRepository = Mockito.mock(ExtractRepository::class.java)
        val modelClass = InvestmentsViewModel::class.java

        val factory = InvestmentsViewModelFactory(mockRepository)

        val viewModel = factory.create(modelClass)

        assertEquals(InvestmentsViewModel::class.java, viewModel.javaClass)
    }
}
