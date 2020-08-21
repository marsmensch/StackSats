package com.androdevlinux.percy.stackingsats.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TestUtils {

    @JvmStatic
    fun main(args: Array<String>) {
        val dateString = "2020-08-16T15:18:31Z"
        val date1 = dateString.getDateWithServerTimeStamp()
        println("String To Date Conversion " +date1.toString())
    }

    fun String.getDateWithServerTimeStamp(): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
            Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")  // IMP !!!
        val dateFormatTo = SimpleDateFormat("EEE, MMM d h:mm a",
            Locale.getDefault())
        dateFormatTo.timeZone = TimeZone.getDefault()
        return try {

            dateFormatTo.format(dateFormat.parse(this)!!)
        } catch (e: ParseException) {
            null
        }
    }
}