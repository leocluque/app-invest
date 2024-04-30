package com.example.home_invest.home

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.example.home_invest.databinding.FragmentHomeBinding
import com.example.home_invest.ui.home.home.HomeFragment
import com.example.home_invest.ui.home.home.HomeViewModel
import com.example.home_invest.ui.home.home.HomeViewModelFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class HomeFragmentTest {

    private lateinit var scenario: FragmentScenario<HomeFragment>

    private val mockViewModelFactory = mock<HomeViewModelFactory>()
    private val mockViewModel = mock<HomeViewModel>()
    private lateinit var mockBinding: FragmentHomeBinding // Removido a inicialização direta

    @Before
    fun setUp() {
        // Configurando o comportamento do mock da fábrica para retornar o mock do ViewModel
        whenever(mockViewModelFactory.create(HomeViewModel::class.java)).thenReturn(mockViewModel)

        // Inflar o layout de teste
        mockBinding =
            FragmentHomeBinding.inflate(LayoutInflater.from(RuntimeEnvironment.application))

        scenario = launchFragmentInContainer { HomeFragment() }
        scenario.onFragment { fragment ->
            fragment.homeViewModel = mockViewModel
            fragment.binding = mockBinding
        }
    }

    @Test
    fun `test setBalance`() {
        val expectedBalance = "100.00"
        scenario.onFragment { fragment ->
            fragment.setBalance(expectedBalance)
        }
        assertEquals(expectedBalance, mockBinding.balanceValueTv.text)
    }

    @Test
    fun `test setLoading`() {
        scenario.onFragment { fragment ->
            fragment.setLoading(true)
        }
        assertEquals(View.VISIBLE, mockBinding.loadingPb.visibility)
        assertEquals(View.GONE, mockBinding.homeContentCl.visibility)
    }
}
