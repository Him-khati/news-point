package com.himanshu.newspoint.data.models.otp

import com.google.gson.annotations.SerializedName

class OtpResponse(
    @SerializedName("isOtpVerified")
    val isOtpVerified: Boolean
) {
}