package com.androdevlinux.percy.stackingsats.ui.buySettings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager

class BuySettingsViewModel(application: Application) : AndroidViewModel(application) {

    val appPreferenceManager: AppPreferenceManager = AppPreferenceManager(application)

    private val amountInInr = MutableLiveData<String>().apply {
        value = appPreferenceManager.inrAmount
    }

    val amountInInrText: LiveData<String> = amountInInr
}