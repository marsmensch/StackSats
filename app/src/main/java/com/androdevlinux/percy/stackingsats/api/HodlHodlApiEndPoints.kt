package com.androdevlinux.percy.stackingsats.api

import com.androdevlinux.percy.stackingsats.pojo.notifications.NotificationsResponseBean
import com.androdevlinux.percy.stackingsats.pojo.offers.post.CreateOfferBodyBean
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface HodlHodlApiEndPoints {
    @POST("api/v1/offers")
    fun createOffer(
        @Header("Authorization") token: String,
        @Body createOfferBodyBean: CreateOfferBodyBean
    ): Call<ResponseBody>

    @POST("api/v1/notifications/read")
    fun getNotifications(
        @Header("Authorization") token: String
    ): Call<NotificationsResponseBean>

    @POST("/api/v1/contracts/{id}/confirm/")
    fun confirmEscrowValidity(
        @Path(
            value = "id",
            encoded = true
        ) contractId: String?,
        @Header("Authorization") token: String
    ): Call<ResponseBody>

    @GET("/api/v1/contracts/my?filters[status]=pending")
    fun listingPendingContracts(
        @Header("Authorization") token: String
    ): Call<ResponseBody>

    @GET("/api/v1/contracts/my?filters[status]=in_progress")
    fun listingInProgressContracts(
        @Header("Authorization") token: String
    ): Call<ResponseBody>

    @GET("/api/v1/payment_method_instructions/{id}")
    fun paymentMethodInstructions(
        @Path(
            value = "id",
            encoded = true
        ) methodId: String?,
        @Header("Authorization") token: String
    ): Call<ResponseBody>
}