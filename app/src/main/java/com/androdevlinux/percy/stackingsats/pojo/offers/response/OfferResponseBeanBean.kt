package com.androdevlinux.percy.stackingsats.pojo.offers.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OfferResponseBeanBean {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("offer")
    @Expose
    var offer: Offer? = null

}