package com.androdevlinux.percy.stackingsats.pojo.exchange

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExchangeRateProvidersResponseBean {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("exchange_rate_providers")
    @Expose
    var exchangeRateProviders: List<ExchangeRateProvider>? = null

}