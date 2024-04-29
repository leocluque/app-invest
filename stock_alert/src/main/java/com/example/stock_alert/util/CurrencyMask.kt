package com.example.stock_alert.util

object CurrencyMask {
    fun unmask(s: String): String {
        return s.replace("[^0-9]".toRegex(), "")
    }
}