package com.example.home_invest.builder

import android.content.Context
import android.content.Intent
import com.example.home_invest.ui.home.HomeActivity

object HomeBuilder {

    fun openModule(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }
}