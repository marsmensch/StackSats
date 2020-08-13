package com.androdevlinux.percy.stackingsats.pojo.offers.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Offer {
    @SerializedName("asset_code")
    @Expose
    var assetCode: String? = null

    @SerializedName("currency_code")
    @Expose
    var currencyCode: String? = null

    @SerializedName("price_source")
    @Expose
    var priceSource: String? = null

    @SerializedName("exchange_rate_provider")
    @Expose
    var exchangeRateProvider: String? = null

    @SerializedName("exchange_price_currency_id")
    @Expose
    var exchangePriceCurrencyId: String? = null

    @SerializedName("exchange_price_sign")
    @Expose
    var exchangePriceSign: String? = null

    @SerializedName("exchange_price_deviation")
    @Expose
    var exchangePriceDeviation: String? = null

    @SerializedName("exchange_price_unit")
    @Expose
    var exchangePriceUnit: String? = null

    @SerializedName("price")
    @Expose
    var price: Float? = null

    @SerializedName("balance")
    @Expose
    var balance: Long? = null

    @SerializedName("side")
    @Expose
    var side: String? = null

    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null

    @SerializedName("min_amount")
    @Expose
    var minAmount: Float? = null

    @SerializedName("max_amount")
    @Expose
    var maxAmount: Float? = null

    @SerializedName("first_trade_limit")
    @Expose
    var firstTradeLimit: Long? = null

    @SerializedName("for_experienced_users")
    @Expose
    var forExperiencedUsers: Boolean? = null

    @SerializedName("confirmations")
    @Expose
    var confirmations: Long? = null

    @SerializedName("payment_window_minutes")
    @Expose
    var paymentWindowMinutes: Long? = null

    @SerializedName("working_hours_start_utc")
    @Expose
    var workingHoursStartUtc: String? = null

    @SerializedName("working_hours_end_utc")
    @Expose
    var workingHoursEndUtc: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("enabled")
    @Expose
    var enabled: Boolean? = null

    @SerializedName("private")
    @Expose
    var private: Boolean? = null

    @SerializedName("payment_method_ids")
    @Expose
    var paymentMethodIds: List<Long>? = null

}