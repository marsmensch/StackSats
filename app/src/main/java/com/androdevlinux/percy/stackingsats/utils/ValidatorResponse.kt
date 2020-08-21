package com.androdevlinux.percy.stackingsats.utils

class ValidatorResponse (var status: String) {

    var responseMessage : String? = null

    fun isFormValid() : Boolean {
        return status == STATUS_OK
    }

    companion object {
        val STATUS_OK = "STATUS_OK"
        val STATUS_ERROR = "STATUS_ERROR"
    }
}