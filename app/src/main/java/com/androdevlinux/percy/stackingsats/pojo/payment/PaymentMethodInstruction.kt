package com.androdevlinux.percy.stackingsats.pojo.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentMethodInstruction {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("version")
    @Expose
    var version: String? = null

    @SerializedName("payment_method_id")
    @Expose
    var paymentMethodId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("details")
    @Expose
    var details: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}