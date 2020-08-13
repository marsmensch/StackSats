package com.androdevlinux.percy.stackingsats.pojo.exchange

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExchangeRateProvider {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("currency_codes")
    @Expose
    var currencyCodes: List<String>? = null

}