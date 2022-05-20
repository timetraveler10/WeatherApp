package com.example.weatherapp.utils

import android.annotation.SuppressLint
import java.text.Format
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    @SuppressLint("SimpleDateFormat")
    fun parseTime(format: String, time: String): String {
        try {

            return when (format) {
                "EEEE" -> {
                    val date = Date()
                    SimpleDateFormat("EEEE").format(date)
                }
                "hh aa" -> {

                    val date = SimpleDateFormat("HH").parse(time)
                    val sdf = SimpleDateFormat(format)

                    sdf.format(date)
                }
                else -> {
                    ""
                }

            }
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }
    }


    fun setDateInWeek(date:String): String {
        val f: Format = SimpleDateFormat("EEEE");
        val date1 = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return f.format(date1)
    }

}