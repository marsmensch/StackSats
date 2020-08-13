package com.androdevlinux.percy.stackingsats.utils

import android.content.Context
import android.content.pm.PackageManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object KotlinUtil {

    fun String.getDateWithServerTimeStamp(): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
        val dateFormatTo = SimpleDateFormat("EEE, MMM d h:mm a",
            Locale.getDefault())
        dateFormatTo.timeZone = TimeZone.getDefault()
        return try {
            dateFormatTo.format(dateFormat.parse(this)!!)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Get current application version.
     */
    fun getAppVersion(context: Context): String? {
        var strVersion: String? = null
        val manager = context.packageManager
        try {
            val info = manager.getPackageInfo(context.packageName, 0)
            strVersion = info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return strVersion
    }
}