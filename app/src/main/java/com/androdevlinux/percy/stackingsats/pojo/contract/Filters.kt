package com.androdevlinux.percy.stackingsats.pojo.contract

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Filters {
    @SerializedName("status")
    @Expose
    var status: String? = null
}