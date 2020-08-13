package com.androdevlinux.percy.stackingsats.pojo.notifications

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationsResponseBean {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("notifications")
    @Expose
    var notifications: List<Notification>? =
        null

}