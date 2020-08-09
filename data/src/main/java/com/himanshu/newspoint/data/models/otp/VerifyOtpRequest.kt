package com.himanshu.newspoint.data.models.otp

import com.google.gson.annotations.SerializedName


data class VerifyOtpRequest (
    @SerializedName("otpToken")
    val otpToken : String? = null,

    @SerializedName("otp")
    val otp : String? = null
)