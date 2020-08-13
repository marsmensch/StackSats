package com.androdevlinux.percy.stackingsats.pojo.offers.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Fee {
    @SerializedName("author_fee_rate")
    @Expose
    var authorFeeRate: String? = null

    @SerializedName("your_fee_rate")
    @Expose
    var yourFeeRate: String? = null

    @SerializedName("transaction_fee")
    @Expose
    var transactionFee: String? = null

    @SerializedName("exchange_fee")
    @Expose
    var exchangeFee: String? = null

}