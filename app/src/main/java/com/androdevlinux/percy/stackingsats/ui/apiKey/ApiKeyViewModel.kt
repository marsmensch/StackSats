package com.androdevlinux.percy.stackingsats.ui.apiKey

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager

class ApiKeyViewModel (application: Application) : AndroidViewModel(application) {

    val appPreferenceManager: AppPreferenceManager = AppPreferenceManager(getApplication())

    private val authorizationToken = MutableLiveData<String>().apply {
        value = appPreferenceManager.authorizationToken
    }

    val authorizationTokenText: LiveData<String> = authorizationToken
}