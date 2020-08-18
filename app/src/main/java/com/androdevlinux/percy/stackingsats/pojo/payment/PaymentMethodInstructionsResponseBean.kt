package com.androdevlinux.percy.stackingsats.pojo.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentMethodInstructionsResponseBean {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("payment_method_instructions")
    @Expose
    var paymentMethodInstructions: List<PaymentMethodInstruction>? = null
}