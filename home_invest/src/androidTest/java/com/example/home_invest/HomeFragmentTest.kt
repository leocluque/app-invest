package com.example.home_invest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.home_invest.ui.home.HomeFragment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Test
    fun testTextViewDisplayed() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.homeBalanceTitleTv))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testTextViewText() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.homeBalanceTitleTv))
            .check(matches(withText(R.string.total_balance)))
    }

    @Test
    fun testButtonClick() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.infoIv))
            .perform(click())
    }

    @Test
    fun testMyInvestmentsTextViewDisplayed() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.myInvestmentsTv))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testMyInvestmentsTextViewText() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.myInvestmentsTv))
            .check(matches(withText(R.string.my_investments)))
    }

    @Test
    fun testViewPagerDisplayed() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.pagesVp))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSwipeViewPager() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        // Deslize para a próxima página
        onView(withId(R.id.pagesVp))
            .perform(swipeLeft())

        // Verifique se a página correta está sendo exibida
        onView(withText("Abril 2024")).check(matches(isDisplayed()))
    }

    @Test
    fun testTabBarDisplayed() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.tabs))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testClickFirstTab() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        // Clique na primeira aba
        onView(withText(R.string.product_title)).perform(click())

        // Verifique se o conteúdo correto é exibido após clicar na primeira aba
        onView(withText("Todos os Produtos")).check(matches(isDisplayed()))
    }

    @Test
    fun testClickSecondTab() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        // Clique na segunda aba
        onView(withText(R.string.wallets_title)).perform(click())

        // Verifique se o conteúdo correto é exibido após clicar na segunda aba
        onView(withText("Total Investido")).check(matches(isDisplayed()))
    }
}
