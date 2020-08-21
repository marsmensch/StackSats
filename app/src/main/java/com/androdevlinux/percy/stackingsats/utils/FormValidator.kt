package com.androdevlinux.percy.stackingsats.utils

import android.content.Context
import android.text.TextUtils
import com.androdevlinux.percy.stackingsats.R


class FormValidator(private val context: Context) {

    var isFormValid: Boolean  = true


    fun validatePin(pinString: String, pinLength: Int): ValidatorResponse{
        val pinValidatorResponse = ValidatorResponse(ValidatorResponse.STATUS_OK)
        var errorResponseMessage = ""

        if(TextUtils.isEmpty(pinString.trim())){
            errorResponseMessage = context.getString(R.string.error_pin_blank_pin)
            pinValidatorResponse.status = ValidatorResponse.STATUS_ERROR
            isFormValid = false

        } else if(pinString.trim().length < pinLength){
            errorResponseMessage = String.format(context.getString(R.string.error_pin_invalid_pin_length), pinLength)
            pinValidatorResponse.status = ValidatorResponse.STATUS_ERROR
            isFormValid = false
        }
        pinValidatorResponse.responseMessage = errorResponseMessage
        return pinValidatorResponse
    }
}