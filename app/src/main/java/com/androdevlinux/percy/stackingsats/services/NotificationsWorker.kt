package com.androdevlinux.percy.stackingsats.services

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.androdevlinux.percy.stackingsats.api.HodlHodlApiClient
import com.androdevlinux.percy.stackingsats.api.HodlHodlApiEndPoints
import com.androdevlinux.percy.stackingsats.pojo.notifications.NotificationsResponseBean
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager
import com.androdevlinux.percy.stackingsats.utils.NotificationHelper
import retrofit2.Call
import retrofit2.Response

class NotificationsWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    private val appPreferenceManager = AppPreferenceManager(applicationContext)
    private val service =
        HodlHodlApiClient().getClient(applicationContext)?.create(HodlHodlApiEndPoints::class.java)

    override fun doWork(): Result {

        val offer = service!!.getNotifications(
            "Bearer " + appPreferenceManager.authorizationToken
        )

        offer.enqueue(object : retrofit2.Callback<NotificationsResponseBean> {
            override fun onResponse(
                call: Call<NotificationsResponseBean>,
                response: Response<NotificationsResponseBean>
            ) {
                if (response.code() == 200) {
                    for (notification in response.body()!!.notifications!!) {
                        NotificationHelper(applicationContext).createNotification(
                            notification.title!!,
                            notification.body!!,
                            "",
                            "stacking_notification"
                        )
                        Log.d("onResponse", notification.link!!)
                    }
                }
            }

            override fun onFailure(
                call: Call<NotificationsResponseBean>,
                t: Throwable
            ) {
                t.printStackTrace()
            }
        })

        return Result.success()
    }
}