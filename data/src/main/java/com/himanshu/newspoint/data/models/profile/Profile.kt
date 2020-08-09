package com.himanshu.newspoint.data.models.profile


import java.util.*

data class Profile(
    var name: String,
//    var gender: Gender? = null,
    var mobileNumber: String,
    var cityId: String,
    var cityName: String,
    var profilePicUpdated: Boolean = false,
    var resumeUpdated: Boolean = false,
    var email: String? = null,
    var pinCode: Int? = null,
    var dateOfBirth: Date? = null,
    var fullAddress: String? = null,
  //  var education: Education? = null,
    var designation: String? = null,
    var experienceInMonths: Int? = null,
    var currentCtc: Double? = null,
    var workingCityId: String? = null,
    var workingCityName: String? = null,
    var designationName: String? = null,
    var designationId: String? = null,
    var aadharStatus: String? = null,
    var panStatus: String? = null,
    var resumeStatus: String? = null,
    var profilePic: String? = null
)