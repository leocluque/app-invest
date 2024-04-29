package com.example.app_invest.base

import android.app.Application
import android.content.Intent
import com.example.app_invest.ui.splash.SplashActivity
import com.example.home_invest.builder.HomeBuilder
import com.example.network.data.create_service.UserUnauthorizedBus
import com.example.network.data.local.PreferencesHelper

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setPreferencesHelper()
        setBus()
        HomeBuilder.executeDepInject()
    }

    private fun setPreferencesHelper() {
        PreferencesHelper.init(this)
    }

    private fun setBus() {
        UserUnauthorizedBus.getEvents().observeForever { unauthorised ->
            when (unauthorised) {
                true -> {
                    PreferencesHelper.basicAuth = ""
                    val intent = Intent(applicationContext, SplashActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }

                else -> {
                }
            }
        }
    }
}
