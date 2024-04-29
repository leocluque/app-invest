package com.example.home_invest.ui.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun String.dateFromString(dateFormat: String): Date? {
    return try {
        val sdf = SimpleDateFormat(dateFormat)
        sdf.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Date.isSameDay(date: Date): Boolean {
    val cal1 = Calendar.getInstance()
    cal1.time = this
    val cal2 = Calendar.getInstance()
    cal2.time = date
    return cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) &&
            cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
}

fun String.changeDateFormat(
    currentDateFormat: String,
    newDateFormat: String,
    locale: Locale = Locale(LANGUAGE_PT, COUNTRY_BR),
    isUTC: Boolean = false
): String {
    return try {
        val oldDf = SimpleDateFormat(currentDateFormat, locale)
        val date = oldDf.parse(this) ?: Calendar.getInstance(locale)
        val newDf = SimpleDateFormat(newDateFormat, locale)

        if (isUTC)
            newDf.timeZone = TimeZone.getTimeZone("gmt")

        newDf.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
