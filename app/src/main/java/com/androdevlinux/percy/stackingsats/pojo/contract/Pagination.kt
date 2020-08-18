package com.androdevlinux.percy.stackingsats.pojo.contract

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pagination {
    @SerializedName("limit")
    @Expose
    var limit: Long? = null

    @SerializedName("offset")
    @Expose
    var offset: Long? = null
}