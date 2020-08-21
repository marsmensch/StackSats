package com.androdevlinux.percy.stackingsats.ui.buyHistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androdevlinux.percy.stackingsats.api.HodlHodlApiClient
import com.androdevlinux.percy.stackingsats.api.HodlHodlApiEndPoints
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyOrderHistoryViewModel (application: Application) : AndroidViewModel(application) {
    private val appPreferenceManager = AppPreferenceManager(application)
    private val service =
        HodlHodlApiClient().getClient(application)?.create(HodlHodlApiEndPoints::class.java)

    fun listingAllContracts(): LiveData<Response<ResponseBody>> {
        val data: MutableLiveData<Response<ResponseBody>> =
            MutableLiveData()
        if (!appPreferenceManager.authorizationToken.isNullOrEmpty()) {
            service!!.listingAllBuyContracts("Bearer " + appPreferenceManager.authorizationToken!!)
                .enqueue(object :
                    Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        data.value = response
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                        data.value = null
                    }
                })
            return data
            }
        return data
    }
}