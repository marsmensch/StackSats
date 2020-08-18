package com.androdevlinux.percy.stackingsats.pojo.contract

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Escrow {
    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("witness_script")
    @Expose
    var witnessScript: String? = null

    @SerializedName("index")
    @Expose
    var index: Long? = null

    @SerializedName("you_confirmed")
    @Expose
    var youConfirmed: Boolean? = null

    @SerializedName("counterparty_confirmed")
    @Expose
    var counterpartyConfirmed: Boolean? = null

    @SerializedName("confirmations")
    @Expose
    var confirmations: Long? = null

    @SerializedName("amount_deposited")
    @Expose
    var amountDeposited: String? = null

    @SerializedName("amount_released")
    @Expose
    var amountReleased: Any? = null

    @SerializedName("deposit_transaction_id")
    @Expose
    var depositTransactionId: Any? = null

    @SerializedName("release_transaction_id")
    @Expose
    var releaseTransactionId: Any? = null
}