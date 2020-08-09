package com.himanshu.newspoint.data.dataStores.userProfile

import com.vinners.newspoint.data.models.profile.UpdateProfileRequest

interface UserProfileRemoteDataSource {


    suspend fun updateProfileData(profileRequest: UpdateProfileRequest)

}