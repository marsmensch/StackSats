package com.androdevlinux.percy.stackingsats.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponseBean {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("error_code")
    @Expose
    var errorCode: String? = null

}