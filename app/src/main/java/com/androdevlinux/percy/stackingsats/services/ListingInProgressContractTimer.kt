package com.androdevlinux.percy.stackingsats.services

import android.content.Context
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager
import java.util.*

class ListingInProgressContractTimer(private var context: Context) : TimerTask() {

    private fun toDo() {
        if (!AppPreferenceManager(context).authorizationToken.isNullOrEmpty()) {
            val myWorkBuilder = OneTimeWorkRequest.Builder(
                ListInProgressContractWorker::class.java
            )
            val myWork = myWorkBuilder.build()
            WorkManager.getInstance(context)
                .enqueue(myWork)
        } else {
            Log.d("NotificationsTimerTask", "authorizationToken is empty")
        }
    }

    override fun run() {
        toDo()
    }
}