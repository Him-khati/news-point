package com.himanshu.newspoint.cache.entityMappers

import com.vinners.newspoint.cache.entities.CachedProfile
import com.vinners.newspoint.data.models.profile.Profile
import javax.inject.Inject

class ProfileEntityMapper @Inject constructor() : EntityMapper<CachedProfile, Profile> {

    override fun mapFromCached(type: CachedProfile): Profile {
        return Profile(
            name = type.name,
            mobileNumber = type.mobileNumber,
            email = type.email,
//            gender = type.gender,
            cityId = type.cityId,
            cityName = type.cityName,
            profilePicUpdated = type.profilePicUpdated,
            resumeUpdated = type.resumeUpdated,
            pinCode = type.pinCode,
            dateOfBirth = type.dateOfBirth,
            fullAddress = type.fullAddress,
//            education = type.education,
            designationId = type.designationId,
            designationName = type.designationName,
            experienceInMonths = type.experienceInMonths,
            currentCtc = type.currentCtc,
            workingCityId = type.workingCityId,
            workingCityName = type.workingCityName,
            aadharStatus = type.aadharStatus,
            panStatus = type.panStatus,
            resumeStatus = type.resumeStatus,
            profilePic = type.profilePic
        )
    }

    override fun mapToCached(type: Profile): CachedProfile {
        return CachedProfile(
            name = type.name,
            mobileNumber = type.mobileNumber,
            email = type.email,
//            gender = type.gender,
            cityId = type.cityId,
            cityName = type.cityName,
            profilePicUpdated = type.profilePicUpdated,
            resumeUpdated = type.resumeUpdated,
            pinCode = type.pinCode,
            dateOfBirth = type.dateOfBirth,
            fullAddress = type.fullAddress,
//            education = type.education,
            designationName = type.designationName,
            designationId = type.designationId,
            experienceInMonths = type.experienceInMonths,
            currentCtc = type.currentCtc,
            workingCityId = type.workingCityId,
            workingCityName = type.workingCityName,
            aadharStatus = type.aadharStatus,
            panStatus = type.panStatus,
            resumeStatus = type.resumeStatus,
            profilePic = type.profilePic
        )
    }


}