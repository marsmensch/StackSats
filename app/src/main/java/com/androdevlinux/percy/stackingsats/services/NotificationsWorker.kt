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
import okhttp3.ResponseBody
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
                        var body = ""
                        if (null == notification.body) {
                            body = ""
                        }
                        NotificationHelper(applicationContext).createNotification(
                            notification.title!!,
                            body,
                            "",
                            "stack_notification"
                        )
                        Log.d("onResponse", notification.link!!)

                        if (notification.title!!.matches("New contract to buy.*".toRegex())) {
                            val contractId = notification.link!!.replace("https://hhtestnet.com/contracts/", "")
                            val confirm = service.confirmEscrowValidity(contractId, "Bearer " + appPreferenceManager.authorizationToken)
                            confirm.enqueue(object : retrofit2.Callback<ResponseBody?> {
                                override fun onResponse(
                                    call: Call<ResponseBody?>,
                                    response: Response<ResponseBody?>
                                ) {
                                    if (response.code() == 200) {

                                    }
                                }

                                override fun onFailure(
                                    call: Call<ResponseBody?>,
                                    t: Throwable
                                ) {
                                    t.printStackTrace()
                                }
                            })
                        }
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