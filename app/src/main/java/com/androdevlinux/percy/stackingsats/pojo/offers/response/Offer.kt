package com.androdevlinux.percy.stackingsats.pojo.offers.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Offer {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("version")
    @Expose
    var version: String? = null

    @SerializedName("asset_code")
    @Expose
    var assetCode: String? = null

    @SerializedName("searchable")
    @Expose
    var searchable: Boolean? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null

    @SerializedName("working_now")
    @Expose
    var workingNow: Boolean? = null

    @SerializedName("side")
    @Expose
    var side: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("currency_code")
    @Expose
    var currencyCode: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("min_amount")
    @Expose
    var minAmount: String? = null

    @SerializedName("max_amount")
    @Expose
    var maxAmount: String? = null

    @SerializedName("first_trade_limit")
    @Expose
    var firstTradeLimit: Any? = null

    @SerializedName("fee")
    @Expose
    var fee: Fee? = null

    @SerializedName("balance")
    @Expose
    var balance: String? = null

    @SerializedName("payment_window_minutes")
    @Expose
    var paymentWindowMinutes: Long? = null

    @SerializedName("confirmations")
    @Expose
    var confirmations: Long? = null

    @SerializedName("payment_methods")
    @Expose
    var paymentMethods: List<PaymentMethod>? = null

    @SerializedName("trader")
    @Expose
    var trader: Trader? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("working_hours_start_utc")
    @Expose
    var workingHoursStartUtc: String? = null

    @SerializedName("working_hours_end_utc")
    @Expose
    var workingHoursEndUtc: String? = null

    @SerializedName("enabled")
    @Expose
    var enabled: Boolean? = null

    @SerializedName("private")
    @Expose
    var private: Boolean? = null

    @SerializedName("for_experienced_users")
    @Expose
    var forExperiencedUsers: Boolean? = null

    @SerializedName("price_source")
    @Expose
    var priceSource: String? = null

    @SerializedName("exchange_rate_provider")
    @Expose
    var exchangeRateProvider: Any? = null

    @SerializedName("exchange_price_deviation")
    @Expose
    var exchangePriceDeviation: Any? = null

    @SerializedName("exchange_price_sign")
    @Expose
    var exchangePriceSign: Any? = null

    @SerializedName("exchange_price_unit")
    @Expose
    var exchangePriceUnit: Any? = null

    @SerializedName("configured_max_amount")
    @Expose
    var configuredMaxAmount: String? = null

}