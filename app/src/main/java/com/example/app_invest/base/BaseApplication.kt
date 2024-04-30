package com.example.app_invest.base

import android.app.Application
import com.example.home_invest.builder.HomeBuilder
import com.example.stock_alert.builder.StockAlertBuilder

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        executeDepInjection()
    }


    private fun executeDepInjection() {
        HomeBuilder.executeDepInject()
        StockAlertBuilder.executeDepInject()
    }
}
