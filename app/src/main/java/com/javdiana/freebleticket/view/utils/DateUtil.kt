package com.javdiana.freebleticket.view.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        const val format = "dd/MM/yy"
        val sdf = SimpleDateFormat(format)



        fun dateToString(date: Long): String {
            val d = Date(date)
            return sdf.format(d)
        }

        fun stringToDate(date: String): Long {
            return try {
                val d: Date = sdf.parse(date)
                d.time
            } catch (exception: ParseException) {
                Log.d("TAG", exception.message)
                0L
            }
        }
    }
}