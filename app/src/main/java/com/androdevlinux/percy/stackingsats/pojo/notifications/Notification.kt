package com.androdevlinux.percy.stackingsats.pojo.notifications

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Notification {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("body")
    @Expose
    var body: String? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

}