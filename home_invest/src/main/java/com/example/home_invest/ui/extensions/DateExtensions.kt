package com.example.home_invest.ui.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.util.Calendar
import java.util.TimeZone

private const val SECOND_MILLIS = 1000f
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS
private const val MONTH_MILLIS = 30 * DAY_MILLIS

fun Date.inMillis(): Long {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.timeInMillis
}

fun currentTimeToLong(): Long {
    return System.currentTimeMillis()
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
    return format.format(date)
}


fun String.inMillis(dateFormat: String) =
    this.dateFromString(dateFormat)?.inMillis()

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

fun Calendar.isSameDay(cal: Calendar): Boolean {
    return cal.get(Calendar.DAY_OF_MONTH) == this.get(Calendar.DAY_OF_MONTH) &&
            cal.get(Calendar.MONTH) == this.get(Calendar.MONTH) &&
            cal.get(Calendar.YEAR) == this.get(Calendar.YEAR)
}

fun Calendar.isToday(): Boolean {
    val today = Calendar.getInstance()

    return today.get(Calendar.MONTH) == this.get(Calendar.MONTH)
            && today.get(Calendar.YEAR) == this.get(Calendar.YEAR)
            && today.get(Calendar.DAY_OF_MONTH) == this.get(Calendar.DAY_OF_MONTH)
}

fun Date.isToday(): Boolean {
    val today = Calendar.getInstance()
    val selected = Calendar.getInstance()
    selected.time = this
    return today.get(Calendar.MONTH) == selected.get(Calendar.MONTH) &&
            today.get(Calendar.YEAR) == selected.get(Calendar.YEAR) &&
            today.get(Calendar.DAY_OF_MONTH) == selected.get(Calendar.DAY_OF_MONTH)
}

fun Date.isPast(): Boolean {
    val today = Calendar.getInstance()
    val selected = Calendar.getInstance()
    selected.time = this

    return today.after(selected)
}

@Throws(ParseException::class)
fun String.getYear(format: String): Int {
    val cal = Calendar.getInstance()
    cal.time = SimpleDateFormat(format).parse(this)
    return cal.get(Calendar.YEAR)
}

@Throws(ParseException::class)
fun String.getDay(format: String): Int {
    val cal = Calendar.getInstance()
    cal.time = SimpleDateFormat(format).parse(this)
    return cal.get(Calendar.DAY_OF_MONTH)
}

@Throws(ParseException::class)
fun String.getMonthNumber(format: String, startFromZero: Boolean): Int {
    val cal = Calendar.getInstance()
    cal.time = SimpleDateFormat(format).parse(this)
    return cal.get(Calendar.MONTH) + if (!startFromZero) 1 else 0
}

@Throws(ParseException::class)
fun String.getDate(format: String): Date? {
    val cal = Calendar.getInstance(Locale.getDefault())
    cal.time = SimpleDateFormat(format, Locale.getDefault()).parse(this) ?: cal.time
    return cal.time
}

fun Date.displayName(format: String): String {
    val cal = Calendar.getInstance()
    cal.time = this
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(cal.time)
}
