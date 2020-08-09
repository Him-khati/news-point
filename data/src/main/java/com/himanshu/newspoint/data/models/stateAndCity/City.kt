package com.himanshu.newspoint.data.models.stateAndCity

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("name")
    val cityName: String? = null,

    @SerializedName("id")
    val cityId: String? = null,

    @SerializedName("districtid")
    val districtiId: String? = null
) {
    override fun toString(): String {
        return cityName!!
    }
}