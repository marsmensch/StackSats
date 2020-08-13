package com.androdevlinux.percy.stackingsats.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
        Intent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val resultPendingIntent = PendingIntent.getActivity(mContext,
                0 /* Request code */, Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d(NotificationHelper::class.java.simpleName, "Message data payload: $data")
        val channelId = channelName.decapitalize().replace(" ", "_")
        val notificationBuilder = NotificationCompat.Builder(mContext, channelId)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(resultPendingIntent)
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