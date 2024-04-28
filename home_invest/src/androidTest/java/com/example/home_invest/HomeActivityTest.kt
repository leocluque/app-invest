package com.example.home_invest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.home_invest.ui.home.HomeActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            // Aqui você pode realizar quaisquer inicializações adicionais necessárias antes dos testes
            // Por exemplo, você pode injetar dependências, configurar dados de teste, etc.
        }
    }

    @Test
    fun testTextViewDisplayed() {
        Espresso.onView(withId(R.id.balanceTitleTv))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testTextViewText() {
        Espresso.onView(withId(R.id.balanceTitleTv))
            .check(matches(withText(R.string.total_balance)))
    }

    @Test
    fun testButtonClick() {
        Espresso.onView(withId(R.id.infoIv))
            .perform(click())
    }

    @Test
    fun testMyInvestmentsTextViewDisplayed() {
        Espresso.onView(withId(R.id.myInvestmentsTv))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testMyInvestmentsTextViewText() {
        Espresso.onView(withId(R.id.myInvestmentsTv))
            .check(matches(withText(R.string.my_investments)))
    }

    @Test
    fun testViewPagerDisplayed() {
        Espresso.onView(withId(R.id.pagesVp))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSwipeViewPager() {
        // Deslize para a próxima página
        Espresso.onView(withId(R.id.pagesVp))
            .perform(swipeLeft())

        // Verifique se a página correta está sendo exibida
        Espresso.onView(withText("Texto na próxima página")).check(matches(isDisplayed()))
    }

    @Test
    fun testTabBarDisplayed() {
        Espresso.onView(withId(R.id.tabs))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testClickFirstTab() {
        // Clique na primeira aba
        Espresso.onView(withText(R.string.product_title)).perform(click())

        // Verifique se o conteúdo correto é exibido após clicar na primeira aba
        Espresso.onView(withText("Todos os Produtos")).check(matches(isDisplayed()))
    }

    @Test
    fun testClickSecondTab() {
        // Clique na segunda aba
        Espresso.onView(withText(R.string.wallets_title)).perform(click())

        // Verifique se o conteúdo correto é exibido após clicar na segunda aba
        Espresso.onView(withText("Conteúdo da segunda aba")).check(matches(isDisplayed()))
    }

}