package com.androdevlinux.percy.stackingsats.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val appPreferenceManager: AppPreferenceManager = AppPreferenceManager(getApplication())

    private val nextDateForOfferCreating = MutableLiveData<String>().apply {
        value = appPreferenceManager.nextDateForOfferCreating
    }

    val nextDateForOfferCreatingText: LiveData<String> = nextDateForOfferCreating
}