package com.androdevlinux.percy.stackingsats.pojo.contract

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VolumeBreakdown {
    @SerializedName("goes_to_buyer")
    @Expose
    var goesToBuyer: String? = null

    @SerializedName("exchange_fee")
    @Expose
    var exchangeFee: String? = null

    @SerializedName("exchange_fee_in_fiat")
    @Expose
    var exchangeFeeInFiat: String? = null

    @SerializedName("transaction_fee")
    @Expose
    var transactionFee: String? = null
}