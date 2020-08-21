package com.androdevlinux.percy.stackingsats.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.androdevlinux.percy.stackingsats.R

class NotificationHelper internal constructor(private val mContext: Context) {

    /**
     * Create and push the notification
     */
    fun createNotification(title: String, body: String, data: String, channelName: String) {

        /*Creates an explicit intent for an Activity in your app**/
        // resultIntent = new Intent(mContext , targetActivity.class);
       /* val pendingIntent = NavDeepLinkBuilder(mContext)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.nav_notifications)
            .setArguments(null)
            .createPendingIntent()*/
        Log.d(NotificationHelper::class.java.simpleName, "Message data payload: $data")
        val channelId = channelName.decapitalize().replace(" ", "_")
        val notificationBuilder = NotificationCompat.Builder(mContext, channelId)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
              //  .setContentIntent(pendingIntent)
                .setContentInfo(data)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
        val notificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification Channel is required for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, channelName, NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        val id = System.currentTimeMillis().toInt()
        notificationManager.notify(id, notificationBuilder.build())
    }

}