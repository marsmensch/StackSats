package com.androdevlinux.percy.stackingsats.api

import com.androdevlinux.percy.stackingsats.pojo.exchange.ExchangeRateProvidersResponseBean
import com.androdevlinux.percy.stackingsats.pojo.notifications.NotificationsResponseBean
import com.androdevlinux.percy.stackingsats.pojo.offers.post.CreateOfferBodyBean
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface HodlHodlApiEndPoints {
    @POST("api/v1/offers")
    fun createOffer(
        @Header("Authorization") token: String,
        @Body createOfferBodyBean: CreateOfferBodyBean
    ): Call<ResponseBody>

/*    @GET("api/v1/payment_methods?filters[country]=India")
    fun listPaymentMethods(
        @Header("Authorization") token: String
    ): Call<ResponseBody>*/

    @POST("api/v1/notifications/read")
    fun getNotifications(
        @Header("Authorization") token: String
    ): Call<NotificationsResponseBean>

    @GET("api/v1/exchange_rate_providers")
    fun listExchanges(
        @Header("Authorization") token: String
    ): Call<ExchangeRateProvidersResponseBean>
}