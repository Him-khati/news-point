package com.himanshu.newspoint.data.models.password

import com.google.gson.annotations.SerializedName


data class ChangePasswordRequest (
    @SerializedName("oldPassword")
    val oldPassword : String,

    @SerializedName("newPassword")
    val newPassword : String
)