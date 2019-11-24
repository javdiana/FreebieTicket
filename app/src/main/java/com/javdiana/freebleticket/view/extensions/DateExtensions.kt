package com.javdiana.freebleticket.view.extensions

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val DAY_OF_WEEK_MAX = "EEEE"
private const val DAY_OF_WEEK_MIN = "EEE"
private const val DAY_OF_MONTH = "dd"
private const val MONTH_MAX = "MMMM"
private const val MONTH_MIN = "MMM"
private const val TIME = "Ha"
private const val YEAR = "yyyy"

private const val FORMAT_FOR_PARSE = "dd-MM-yyyy Ha"
private const val FORMAT_FOR_DETAILS =
    "$DAY_OF_WEEK_MAX, $DAY_OF_MONTH $MONTH_MIN $YEAR • $TIME - $TIME"
private lateinit var sdf: SimpleDateFormat

fun String.formatToLong(): Long {
    sdf = SimpleDateFormat(FORMAT_FOR_PARSE, Locale.ENGLISH)
    return try {
        val d: Date = sdf.parse(this)!!
        d.time
    } catch (exception: ParseException) {
        Log.d("TAG", exception.message!!)
        0L
    }
}

fun Long.formatToLongForDetails(): String {
    sdf = SimpleDateFormat(FORMAT_FOR_DETAILS, Locale.ENGLISH)
    val d = Date(this)
    return sdf.format(d)
}

fun Int.getNextDateToLong(): Long {
    sdf = SimpleDateFormat(MONTH_MIN, Locale.ENGLISH)
    val c = Calendar.getInstance()
    c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + this)
    return c.timeInMillis
}

fun Long.formatToString(): String {
    sdf = SimpleDateFormat(FORMAT_FOR_PARSE, Locale.ENGLISH)
    val d = Date(this)
    return sdf.format(d)
}

fun Long.getDayOfMonth(): String {
    sdf = SimpleDateFormat(DAY_OF_MONTH, Locale.ENGLISH)
    val d = Date(this)
    return sdf.format(d)
}

fun Long.getDayOfWeek(): String {
    sdf = SimpleDateFormat(DAY_OF_WEEK_MIN, Locale.ENGLISH)
    val d = Date(this)
    return sdf.format(d)
}

fun Long.getMonth(): String {
    sdf = SimpleDateFormat(MONTH_MIN, Locale.ENGLISH)
    val d = Date(this)
    return sdf.format(d)
}

fun Long.getMonthDayYear(): String {
    sdf = SimpleDateFormat("$MONTH_MAX $DAY_OF_MONTH, $YEAR", Locale.ENGLISH)
    val d = Date(this)
    return sdf.format(d)
}
