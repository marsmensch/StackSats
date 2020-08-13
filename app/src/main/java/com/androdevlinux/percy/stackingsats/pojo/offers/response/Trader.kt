package com.androdevlinux.percy.stackingsats.pojo.offers.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Trader {
    @SerializedName("login")
    @Expose
    var login: String? = null

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null

    @SerializedName("online_status")
    @Expose
    var onlineStatus: String? = null

    @SerializedName("rating")
    @Expose
    var rating: Any? = null

    @SerializedName("trades_count")
    @Expose
    var tradesCount: Long? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("verified")
    @Expose
    var verified: Boolean? = null

    @SerializedName("verified_by")
    @Expose
    var verifiedBy: Any? = null

    @SerializedName("strong_hodler")
    @Expose
    var strongHodler: Boolean? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null

    @SerializedName("average_payment_time_minutes")
    @Expose
    var averagePaymentTimeMinutes: Any? = null

    @SerializedName("average_release_time_minutes")
    @Expose
    var averageReleaseTimeMinutes: Any? = null

    @SerializedName("days_since_last_trade")
    @Expose
    var daysSinceLastTrade: Any? = null

    @SerializedName("blocked_by")
    @Expose
    var blockedBy: Long? = null

}