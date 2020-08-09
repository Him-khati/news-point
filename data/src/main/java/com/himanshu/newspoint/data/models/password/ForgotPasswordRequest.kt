package com.himanshu.newspoint.data.models.password

import com.google.gson.annotations.SerializedName


data class ForgotPasswordRequest (
    @SerializedName("mobile")
    val mobile : String
)