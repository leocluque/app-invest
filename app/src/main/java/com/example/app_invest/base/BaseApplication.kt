package com.example.app_invest.base

import android.app.Application
import android.content.Intent
import com.example.app_invest.ui.SplashActivity
import com.example.network.data.create_service.UserUnauthorizedBus
import com.example.network.local.PreferencesHelper

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setPreferencesHelper()
        setBus()
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
