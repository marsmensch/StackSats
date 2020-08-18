package com.androdevlinux.percy.stackingsats.services

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.androdevlinux.percy.stackingsats.api.HodlHodlApiClient
import com.androdevlinux.percy.stackingsats.api.HodlHodlApiEndPoints
import com.androdevlinux.percy.stackingsats.pojo.ErrorResponseBean
import com.androdevlinux.percy.stackingsats.pojo.contract.ListingContractResponseBean
import com.androdevlinux.percy.stackingsats.pojo.payment.PaymentMethodInstructionsResponseBean
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager
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

class ListInProgressContractWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    private val appPreferenceManager = AppPreferenceManager(applicationContext)
    private val service =
        HodlHodlApiClient().getClient(applicationContext)?.create(HodlHodlApiEndPoints::class.java)

    override fun doWork(): Result {
        val listContract = service!!.listingInProgressContracts(
            "Bearer " + appPreferenceManager.authorizationToken
        )

        listContract.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    val myType = object : TypeToken<ListingContractResponseBean>() {}.type
                    val responseList =
                        Gson().fromJson<ListingContractResponseBean>(
                            response.body()!!.charStream(),
                            myType
                        )
                    if (responseList.contracts!!.isNotEmpty()) {
                        val paymentMethodInstructions = service.paymentMethodInstructions(
                            responseList.contracts!!.first().paymentMethodInstruction!!.paymentMethodId,
                            "Bearer " + appPreferenceManager.authorizationToken
                        )
                        paymentMethodInstructions.enqueue(object :
                            retrofit2.Callback<ResponseBody> {
                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                if (response.code() == 200) {
                                    val paymentMethodInstructionsType = object :
                                        TypeToken<PaymentMethodInstructionsResponseBean>() {}.type
                                    val paymentMethodInstructionsTypeResponse =
                                        Gson().fromJson<PaymentMethodInstructionsResponseBean>(
                                            response.body()!!.charStream(),
                                            paymentMethodInstructionsType
                                        )
                                    NotificationHelper(applicationContext).createNotification(
                                        "Pay at " + paymentMethodInstructionsTypeResponse!!.paymentMethodInstructions!!.first().name!!,
                                        responseList.contracts!!.first().paymentMethodInstruction!!.details!!,
                                        "",
                                        "stack_payment"
                                    )
                                }
                            }

                            override fun onFailure(
                                call: Call<ResponseBody>,
                                t: Throwable
                            ) {
                                t.printStackTrace()
                            }
                        })
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
            }
        })

        return Result.success()
    }
}