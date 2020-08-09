package com.himanshu.newspoint.data.models.stateAndCity

import com.google.gson.annotations.SerializedName

data class Pincode(
    @SerializedName("id")
    val pincodeId: String? = null,

    @SerializedName("name")
    val pincode: String? = null
) {
    override fun toString(): String {
        return pincode!!
    }
}