package com.himanshu.newspoint.data.models.auth

import com.google.gson.annotations.SerializedName


data class LoginResponse(

    @SerializedName("firstname")
    val firstName: String? = null,

    @SerializedName("lastname")
    val lastName: String,

    @SerializedName("usertype")
    val userType: String,

    @SerializedName("agencyType")
    val agencyType: String,

    @SerializedName("mobile")
    val mobile: String,

    @SerializedName("watsappMobile")
    val whatsAppMobileNumber: String,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("dob")
    val dob: String? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("cityId")
    val cityId: Int? = null,

    @SerializedName("password")
    val password: String,

    @SerializedName("pinCodeId")
    val pinCodeId: Int? = null,

    @SerializedName("gpsAddress")
    val gpsAddress : String? = null,

    @SerializedName("education")
    val education : String? = null,

    @SerializedName("experience")
    val experience : String? = null,

    @SerializedName("languages")
    val languages : String? = null,

    @SerializedName("twoWheeler")
    val twoWheeler : Boolean,

    @SerializedName("workCategory")
    val workCategory : Int? = null,

    @SerializedName("gps")
    val gps : String? = null,

    @SerializedName("designation")
    val designation : String? = null,

    @SerializedName("teamSize")
    val teamSize : String? = null,

    @SerializedName("websitePage")
    val websitePage : String? = null,

    @SerializedName("facebookPage")
    val facebookPage : String? = null
){
    val hasUserFilledRegistrationForm = firstName == null
}