package com.androdevlinux.percy.stackingsats.services

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.androdevlinux.percy.stackingsats.api.HodlHodlApiClient
import com.androdevlinux.percy.stackingsats.api.HodlHodlApiEndPoints
import com.androdevlinux.percy.stackingsats.pojo.ErrorResponseBean
import com.androdevlinux.percy.stackingsats.pojo.offers.post.CreateOfferBodyBean
import com.androdevlinux.percy.stackingsats.pojo.offers.post.Offer
import com.androdevlinux.percy.stackingsats.pojo.offers.response.OfferResponseBeanBean
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager
import com.androdevlinux.percy.stackingsats.utils.KotlinUtil.getDateWithServerTimeStamp
import com.androdevlinux.percy.stackingsats.utils.NotificationHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

class CreateOfferWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val appPreferenceManager = AppPreferenceManager(applicationContext)
    private val service =
        HodlHodlApiClient().getClient(applicationContext)?.create(HodlHodlApiEndPoints::class.java)

    override fun doWork(): Result {
        if (!appPreferenceManager.authorizationToken.isNullOrEmpty() && !appPreferenceManager.inrAmount.isNullOrEmpty()) {
            if (appPreferenceManager.nextDateForOfferCreating.isNullOrEmpty()) {
                execute()
            } else {
                if (appPreferenceManager.weeklyOrMonthly.equals("weekly", ignoreCase = true)) {
                    if (!appPreferenceManager.nextDateForOfferCreating.isNullOrEmpty()) {
                        val dateFormatter: DateTimeFormatter =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val validUntil = appPreferenceManager.nextDateForOfferCreating
                        val validDate = LocalDate.parse(validUntil, dateFormatter)
                        if (!appPreferenceManager.lastOfferDate.isNullOrEmpty()) {
                            val lastOfferValidUntil = appPreferenceManager.lastOfferDate
                            val lastOfferDate = LocalDate.parse(lastOfferValidUntil, dateFormatter)
                            val currentDate: LocalDate = LocalDate.now()
                            if ((currentDate.isAfter(validDate) || currentDate.isEqual(validDate)) && !currentDate.isEqual(
                                    lastOfferDate
                                )
                            ) {
                                execute()
                            } else {
                                Log.d(TAG, "Next week is coming soon")
                            }
                        } else {
                            val currentDate: LocalDate = LocalDate.now()
                            if (currentDate.isAfter(validDate) || currentDate.isEqual(validDate)) {
                                execute()
                            } else {
                                Log.d(TAG, "Next week is coming soon")
                            }
                        }
                    }
                } else if (appPreferenceManager.weeklyOrMonthly.equals(
                        "monthly",
                        ignoreCase = true
                    )
                ) {
                    if (!appPreferenceManager.nextDateForOfferCreating.isNullOrEmpty()) {
                        val dateFormatter: DateTimeFormatter =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val validUntil = appPreferenceManager.nextDateForOfferCreating
                        val validDate = LocalDate.parse(validUntil, dateFormatter)
                        if (!appPreferenceManager.lastOfferDate.isNullOrEmpty()) {
                            val lastOfferValidUntil = appPreferenceManager.lastOfferDate
                            val lastOfferDate = LocalDate.parse(lastOfferValidUntil, dateFormatter)
                            val currentDate: LocalDate = LocalDate.now()
                            if ((currentDate.isAfter(validDate) || currentDate.isEqual(validDate)) && !currentDate.isEqual(
                                    lastOfferDate
                                )
                            ) {
                                execute()
                            } else {
                                Log.d(TAG, "Next month is coming soon")
                            }
                        } else {
                            val currentDate: LocalDate = LocalDate.now()
                            if (currentDate.isAfter(validDate) || currentDate.isEqual(validDate)) {
                                execute()
                            } else {
                                Log.d(TAG, "Next month is coming soon")
                            }
                        }
                    }
                }
            }
        }
        return Result.success()
    }

    private fun execute() {
        val createOfferBodyBean =
            CreateOfferBodyBean()
        val createOffer =
            Offer()
        createOffer.side = "buy"
        createOffer.assetCode = "BTC"
        createOffer.priceSource = "exchange_rate"
        createOffer.exchangeRateProvider = "Bitstamp"
        createOffer.exchangePriceCurrencyId = "22"
        createOffer.exchangePriceSign = "+"
        createOffer.exchangePriceDeviation = "0"
        createOffer.exchangePriceUnit = "%"
        createOffer.countryCode = "IN"
        createOffer.balance = appPreferenceManager.inrAmount!!.toLong()
        createOffer.currencyCode = "INR"
        createOffer.minAmount = appPreferenceManager.inrAmount!!.toFloat()
        createOffer.maxAmount = appPreferenceManager.inrAmount!!.toFloat()
        createOffer.forExperiencedUsers = false
        createOffer.confirmations = 1
        createOffer.paymentWindowMinutes = 1440
        createOffer.workingHoursEndUtc = "09:00"
        createOffer.workingHoursStartUtc = "09:00"
        createOffer.enabled = true
        createOffer.private = false
        createOffer.title = appPreferenceManager.offerTitle
        createOffer.description = appPreferenceManager.offerDescription
        createOffer.paymentMethodIds =
            arrayListOf(70)
        createOfferBodyBean.offer = createOffer

        val offer = service!!.createOffer(
            "Bearer " + appPreferenceManager.authorizationToken,
            createOfferBodyBean
        )

        offer.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    val myType = object : TypeToken<OfferResponseBeanBean>() {}.type
                    val responseList =
                        Gson().fromJson<OfferResponseBeanBean>(
                            response.body()!!.charStream(),
                            myType
                        )

                    if (responseList.status!! == "success") {
                        if (appPreferenceManager.weeklyOrMonthly.equals(
                                "weekly",
                                ignoreCase = true
                            )
                        ) {
                            var ld: LocalDate = LocalDate.now()
                            appPreferenceManager.setLastOfferDate(ld.toString())
                            println(ld)
                            ld = ld.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                            println(ld)
                            appPreferenceManager.setNextDateForOfferCreating(ld.toString())
                        } else if (appPreferenceManager.weeklyOrMonthly.equals(
                                "monthly",
                                ignoreCase = true
                            )
                        ) {
                            var ld: LocalDate = LocalDate.now()
                            appPreferenceManager.setLastOfferDate(ld.toString())
                            println(ld)
                            ld = ld.plusMonths(1).withDayOfMonth(1)
                            println(ld)
                            appPreferenceManager.setNextDateForOfferCreating(ld.toString())
                        }
                        NotificationHelper(applicationContext).createNotification(
                            "New BTC Offer at " + responseList.offer!!.price + "$",
                            "Created at " + responseList.offer!!.createdAt!!.getDateWithServerTimeStamp(),
                            "",
                            "stack_offer"
                        )
                        Log.d("onResponse", responseList.status!!)
                    }
                } else {
                    val errorBody = Gson().fromJson(
                        response.errorBody()!!.charStream(),
                        ErrorResponseBean::class.java
                    )
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.d("onResponse", errorBody.errorCode!!)
                        Toasty.error(applicationContext,  errorBody.status!!, Toast.LENGTH_SHORT, true).show()
                    }

                }
            }

            override fun onFailure(
                call: Call<ResponseBody>,
                t: Throwable
            ) {
                t.printStackTrace()
                Toasty.error(applicationContext, t.printStackTrace().toString(), Toast.LENGTH_SHORT, true).show()
            }
        })
    }

    companion object {
        private const val TAG = "CreateOfferWorker"
    }
}