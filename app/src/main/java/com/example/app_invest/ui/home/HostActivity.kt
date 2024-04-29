package com.example.app_invest.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.app_invest.R
import com.example.app_invest.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {

    internal lateinit var binding: ActivityHostBinding

    internal val bottomNavigation by lazy {
        binding.bottomNavigation
    }
    private val mainHomeFrame by lazy {
        val homeMain = R.id.nav_host_fragment
        homeMain
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        bottomNavigation.setupWithNavController(Navigation.findNavController(this, mainHomeFrame))
        bottomNavigation.selectedItemId = R.id.homeMenuItem
        bottomNavigation.menu.getItem(0).isChecked = true
        bottomNavigation.apply {
            setOnItemSelectedListener { item ->
                if (item.itemId == bottomNavigation.selectedItemId) {
                    return@setOnItemSelectedListener true
                } else when (item.itemId) {
                    R.id.homeMenuItem -> {
                        item.isChecked = true
                        Navigation.findNavController(this@HostActivity, mainHomeFrame)
                            .navigate(R.id.homeFragment)
                    }

                    R.id.stockAlertItem -> {
                        item.isChecked = true
                        Navigation.findNavController(this@HostActivity, mainHomeFrame)
                            .navigate(R.id.stockAlertFragment)
                    }
                }
                return@setOnItemSelectedListener false
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        // nothing
    }
}