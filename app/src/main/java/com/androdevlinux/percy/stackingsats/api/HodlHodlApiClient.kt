package com.androdevlinux.percy.stackingsats.api

import android.content.Context
import com.androdevlinux.percy.stackingsats.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HodlHodlApiClient {
    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder()
        .setLenient()
        .create()
    private val logging = HttpLoggingInterceptor()


    fun getClient(mContext: Context?): Retrofit? {
        if (retrofit == null) {
            // set your desired log level
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …

            // add logging as last interceptor
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor(NetworkConnectionInterceptor(mContext!!))
            httpClient.readTimeout(400, TimeUnit.SECONDS)
            httpClient.callTimeout(400, TimeUnit.SECONDS)
            httpClient.writeTimeout(400, TimeUnit.SECONDS)
            httpClient.connectTimeout(400, TimeUnit.SECONDS)
            httpClient.followRedirects(true)
            httpClient.retryOnConnectionFailure(true)
            retrofit = Retrofit.Builder()
                .baseUrl("https://hhtestnet.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }
}