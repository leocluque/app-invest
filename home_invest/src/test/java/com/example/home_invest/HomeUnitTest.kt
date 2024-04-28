package com.example.home_invest

import android.content.Context
import android.view.LayoutInflater
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.test.core.app.ActivityScenario
import androidx.viewpager.widget.ViewPager
import com.example.home_invest.databinding.ActivityHomeBinding
import com.example.home_invest.ui.components.CustomViewPager
import com.example.home_invest.ui.home.HomeActivity
import com.example.home_invest.ui.home.product.ProductFragment
import com.example.home_invest.ui.home.wallets.WalletsFragment
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class HomeActivityTest {

    @Mock
    private lateinit var mockFragmentManager: FragmentManager

    @Mock
    private lateinit var mockFragmentTransaction: FragmentTransaction

    @Mock
    private lateinit var mockBinding: ActivityHomeBinding

    private lateinit var activity: HomeActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = HomeActivity()
        Robolectric.buildActivity(HomeActivity::class.java)
        val mockContext = mock(Context::class.java)

    }

    @Test
    fun testSetView() {
        // Set up the activity
        activity.binding = mockBinding

        // Mocking setup for fragments
        val mockProductFragment = ProductFragment()
        val mockWalletsFragment = WalletsFragment()
        val fragments = listOf(mockProductFragment, mockWalletsFragment)

        // Mocking setup for view pager adapter
        val mockViewPager = CustomViewPager(mockFragmentManager, activity)
        `when`(mockBinding.balanceValueTv.text).thenReturn("some value")

//        `when`(mockBinding.pagesVp).thenReturn(mockViewPager as ViewPager)



        // Call the method to be tested
        activity.setView()

        // Verify that fragments are added to the view pager adapter
        verify(mockViewPager).addFrag(fragments)

        // Verify that balanceValueTv text is set correctly
        verify(mockBinding.balanceValueTv).text = "R$ 160.000,00"
    }

    // Add more tests for other methods as needed
}