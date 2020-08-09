package com.himanshu.newspoint.data.models.otp

import com.google.gson.annotations.SerializedName
import com.vinners.newspoint.data.models.mdm.MobileInformation

data class VerifyOtpLoginRequest(
    @SerializedName("token")
    val otpToken : String? = null,

    @SerializedName("otp")
    val otp : String? = null,

    @SerializedName("mobileInfo")
    val mobileInfo : MobileInformation? = null
) {
}