package com.androdevlinux.percy.stackingsats.pojo.offers.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentMethod {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

}