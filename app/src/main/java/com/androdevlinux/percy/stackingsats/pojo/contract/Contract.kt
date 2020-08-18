package com.androdevlinux.percy.stackingsats.pojo.contract

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contract {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("your_role")
    @Expose
    var yourRole: String? = null

    @SerializedName("can_be_canceled")
    @Expose
    var canBeCanceled: Boolean? = null

    @SerializedName("offer_id")
    @Expose
    var offerId: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null

    @SerializedName("currency_code")
    @Expose
    var currencyCode: String? = null

    @SerializedName("volume")
    @Expose
    var volume: String? = null

    @SerializedName("asset_code")
    @Expose
    var assetCode: String? = null

    @SerializedName("comment")
    @Expose
    var comment: String? = null

    @SerializedName("release_address")
    @Expose
    var releaseAddress: String? = null

    @SerializedName("confirmations")
    @Expose
    var confirmations: Long? = null

    @SerializedName("payment_window_seconds_left")
    @Expose
    var paymentWindowSecondsLeft: Any? = null

    @SerializedName("payment_window_time_left_seconds")
    @Expose
    var paymentWindowTimeLeftSeconds: Any? = null

    @SerializedName("payment_window_minutes")
    @Expose
    var paymentWindowMinutes: Long? = null

    @SerializedName("depositing_window_minutes")
    @Expose
    var depositingWindowMinutes: Long? = null

    @SerializedName("depositing_window_time_left_seconds")
    @Expose
    var depositingWindowTimeLeftSeconds: Any? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("dispute_status")
    @Expose
    var disputeStatus: Any? = null

    @SerializedName("payment_method_instruction")
    @Expose
    var paymentMethodInstruction: PaymentMethodInstruction? = null

    @SerializedName("volume_breakdown")
    @Expose
    var volumeBreakdown: VolumeBreakdown? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("counterparty")
    @Expose
    var counterparty: Counterparty? = null

    @SerializedName("escrow")
    @Expose
    var escrow: Escrow? = null
}