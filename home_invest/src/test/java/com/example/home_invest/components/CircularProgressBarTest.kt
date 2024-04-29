//package com.example.home_invest.components
//
//import android.content.Context
//import android.graphics.Paint
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.home_invest.ui.components.CircularProgressBar
//import com.example.home_invest.ui.components.ProgressItem
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class CircularProgressBarTest {
//
//    private lateinit var circularProgressBar: CircularProgressBar
//
//    @Before
//    fun setUp() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val view = CircularProgressBar(context)
//        circularProgressBar = view
//    }
//
//    @Test
//    fun setItems_CorrectlySetsItems() {
//        val items = listOf(
//            ProgressItem("Product 1", 25f, "#FF0000"),
//            ProgressItem("Product 2", 50f, "#00FF00"),
//            ProgressItem("Product 3", 25f, "#0000FF")
//        )
//        circularProgressBar.setItems(items)
//        assertEquals(items, circularProgressBar.getItems())
//    }
//
//    @Test
//    fun setItems_UpdatesViewOnInvalidation() {
//        val items = listOf(
//            ProgressItem("Product 1", 25f, "#FF0000"),
//            ProgressItem("Product 2", 50f, "#00FF00"),
//            ProgressItem("Product 3", 25f, "#0000FF")
//        )
//        circularProgressBar.setItems(items)
//
//        // Mock Canvas for testing
//        val canvas = CanvasStub()
//        circularProgressBar.onDraw(canvas)
//
//    }
//
//    @Test
//    fun getItems_ReturnsEmptyListInitially() {
//        val expectedItems = emptyList<ProgressItem>()
//        assertEquals(expectedItems, circularProgressBar.getItems())
//    }
//}
//
//class CanvasStub : android.graphics.Canvas() {
//    override fun drawArc(
//        left: Float,
//        top: Float,
//        right: Float,
//        bottom: Float,
//        startAngle: Float,
//        sweepAngle: Float,
//        useCenter: Boolean,
//        paint: Paint
//    ) {
//    }
//}
