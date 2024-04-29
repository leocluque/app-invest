package com.example.home_invest.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import kotlin.coroutines.ContinuationInterceptor

@ExperimentalCoroutinesApi
open class CoroutineTest {

    // Use TestCoroutineDispatcher to control execution of coroutines
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    // Create a TestCoroutineScope to control coroutines
    val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Before
    fun setup() {
        // Set TestCoroutineDispatcher as the main dispatcher for testing
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        // Reset main dispatcher after testing
        Dispatchers.resetMain()
        // Cleanup remaining coroutines
        testCoroutineScope.cleanupTestCoroutines()
    }
}
