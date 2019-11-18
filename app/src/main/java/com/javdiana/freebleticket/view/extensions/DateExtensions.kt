package com.javdiana.freebleticket.view.extensions

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val formatDay = "EEEE"
private const val formatMontH = " MMM D, Ha"
private val sdf = SimpleDateFormat("$formatDay $formatMontH", Locale.ENGLISH)

fun String.formatToLong(): Long {
    return try {
        val d: Date = sdf.parse(this)
        d.time
    } catch (exception: ParseException) {
        Log.d("TAG", exception.message!!)
        0L
    }
}

fun Long.formatToString(): String {
    val d = Date(this)
    return sdf.format(d)
}