package com.androdevlinux.percy.stackingsats.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    val appPreferenceManager: AppPreferenceManager = AppPreferenceManager(getApplication())

    private val authorizationToken = MutableLiveData<String>().apply {
        value = appPreferenceManager.authorizationToken
    }

    private val offerTitle = MutableLiveData<String>().apply {
        value = appPreferenceManager.offerTitle
    }

    private val offerDescription = MutableLiveData<String>().apply {
        value = appPreferenceManager.offerDescription
    }

    val authorizationTokenText: LiveData<String> = authorizationToken

    val offerTitleText: LiveData<String> = offerTitle

    val offerDescriptionText: LiveData<String> = offerDescription
}