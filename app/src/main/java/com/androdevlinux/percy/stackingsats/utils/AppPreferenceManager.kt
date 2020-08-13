package com.androdevlinux.percy.stackingsats.utils

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.androdevlinux.percy.stackingsats.R

class AppPreferenceManager(private val context: Context) {

    private val prefs: SharedPreferences = getEncryptedSharedPreferences()!!

    val authorizationToken: String?
        get() = prefs.getString(
            context.resources.getString(R.string.authorization_token_preference),
            "PcfvGrkOEezeBxXRshEGwRNeRCJj6sHpzWyGMokL4DhBSNxkMVeouEEcQUDImG3gFgljieA2hp97xJCj"
        )

    fun setAuthorizationToken(value: String?) {
        prefs.edit().putString(context.resources.getString(R.string.authorization_token_preference), value!!).apply()
    }

    val inrAmount: String?
        get() = prefs.getString(context.resources.getString(R.string.inr_amount_preference), "1000")

    fun setInrAmount(value: String?) {
        prefs.edit().putString(context.resources.getString(R.string.inr_amount_preference), value!!).apply()
    }

    val onlinePaymentMethodId: Int?
        get() = prefs.getInt(context.resources.getString(R.string.online_payment_method_id_preference), 158)

    fun setOnlinePaymentMethodId(value: Int?) {
        prefs.edit().putInt(context.resources.getString(R.string.online_payment_method_id_preference), value!!).apply()
    }

    val offerTitle: String?
        get() = prefs.getString(context.resources.getString(R.string.offer_title_preference), "")

    fun setOfferTitle(value: String?) {
        prefs.edit().putString(context.resources.getString(R.string.offer_title_preference), value!!).apply()
    }

    val offerDescription: String?
        get() = prefs.getString(context.resources.getString(R.string.offer_description_preference), "")

    fun setOfferDescription(value: String?) {
        prefs.edit().putString(context.resources.getString(R.string.offer_description_preference), value!!).apply()
    }

    val weeklyOrMonthly: String?
        get() = prefs.getString(context.resources.getString(R.string.weekly_or_monthly_preference), "WEEKLY")

    fun setWeeklyOrMonthly(value: String?) {
        prefs.edit().putString(context.resources.getString(R.string.weekly_or_monthly_preference), value!!).apply()
    }

    val nextDateForOfferCreating: String?
        get() = prefs.getString(context.resources.getString(R.string.next_date_for_offer_creating_preference), "")

    fun setNextDateForOfferCreating(value: String?) {
        prefs.edit().putString(context.resources.getString(R.string.next_date_for_offer_creating_preference), value!!).apply()
    }

    val lastOfferDate: String?
        get() = prefs.getString(context.resources.getString(R.string.last_offer_date_preference), "")

    fun setLastOfferDate(value: String?) {
        prefs.edit().putString(context.resources.getString(R.string.last_offer_date_preference), value!!).apply()
    }

    private fun getEncryptedSharedPreferences(): SharedPreferences? {

        val spec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .build()

        val masterKey = MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs_file",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}