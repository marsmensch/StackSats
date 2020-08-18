package com.androdevlinux.percy.stackingsats.pojo.contract

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentMethodInstruction {
    @SerializedName("payment_method_id")
    @Expose
    var paymentMethodId: String? = null

    @SerializedName("details")
    @Expose
    var details: String? = null
}