package com.androdevlinux.percy.stackingsats.pojo.offers.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateOfferBodyBean {
    @SerializedName("offer")
    @Expose
    var offer: Offer? = null

}