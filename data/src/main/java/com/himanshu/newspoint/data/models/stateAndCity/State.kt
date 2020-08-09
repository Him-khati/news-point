package com.himanshu.newspoint.data.models.stateAndCity

import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("name")
    val stateName: String? = null,

    @SerializedName("id")
    val stateId: String? = null
) {
    override fun toString(): String {
        return stateName!!
    }
}