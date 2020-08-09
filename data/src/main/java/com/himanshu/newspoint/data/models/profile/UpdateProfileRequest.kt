package com.himanshu.newspoint.data.models.profile

import com.google.gson.annotations.SerializedName


data class UpdateProfileRequest(

    @SerializedName("name")
    val name: String,

    @SerializedName("mobileNumber")
    val mobileNumber: String,

    @SerializedName("email")
    val email: String?

)