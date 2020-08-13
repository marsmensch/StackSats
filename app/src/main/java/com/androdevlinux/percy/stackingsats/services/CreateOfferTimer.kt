package com.androdevlinux.percy.stackingsats.services

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.*

class CreateOfferTimer (private var context: Context) : TimerTask() {

    private fun toDo() {
        val myWorkBuilder = OneTimeWorkRequest.Builder(
            CreateOfferWorker::class.java
        )
        val myWork = myWorkBuilder.build()
        WorkManager.getInstance(context)
            .enqueue(myWork)
    }

    override fun run() {
        toDo()
    }
}