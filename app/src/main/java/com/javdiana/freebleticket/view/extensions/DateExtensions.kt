package com.javdiana.freebleticket.view.extensions

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val DAY_OF_WEEK_MAX = "EEEE"
private const val DAY_OF_WEEK_MIN = "EEE"
private const val DAY_OF_MONTH = "D"
private const val MONTH_MAX = "MMMM"
private const val MONTH_MIN = "MMM"
private const val TIME = "Ha"
private lateinit var sdf: SimpleDateFormat

fun String.formatToLong(): Long {
    sdf = SimpleDateFormat("$DAY_OF_WEEK_MAX $MONTH_MAX $DAY_OF_MONTH, $TIME", Locale.ENGLISH)
    return try {
        val d: Date = sdf.parse(this)
        d.time
    } catch (exception: ParseException) {
        Log.d("TAG", exception.message!!)
        0L
    }
}

fun Long.formatToString(): String {
    sdf = SimpleDateFormat("$DAY_OF_WEEK_MAX $MONTH_MIN $DAY_OF_MONTH, $TIME", Locale.ENGLISH)
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
