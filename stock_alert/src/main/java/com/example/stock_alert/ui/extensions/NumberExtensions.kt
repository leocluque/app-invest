package com.example.stock_alert.ui.extensions

import java.text.NumberFormat
import java.util.Locale

// GENERAL
const val LANGUAGE_PT = "pt"
const val COUNTRY_BR = "BR"

fun Float.formatToCurrency(locale: Locale): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    return currencyFormatter.format(this.toDouble())
}

fun Float.formatCurrencyBRL(): String {
    val locale = Locale(LANGUAGE_PT, COUNTRY_BR)
    return this.formatToCurrency(locale)
}

fun Float.formatCurrencyBRL(trimRs: Boolean = false): String {
    val locale = Locale(LANGUAGE_PT, COUNTRY_BR)
    return if (trimRs) this.formatToCurrency(locale).replace("R$", "") else this.formatToCurrency(locale)
}