package com.himanshu.newspoint.data.dataStores.userProfile

import com.vinners.newspoint.data.models.profile.Profile
import kotlinx.coroutines.flow.Flow


interface UserProfileLocalDataStore {

    /**
     * Returns An Instance of [Flow] that emit profile object object when you subscribe to it
     * or profile in local changes
     */
    fun getProfile(): Flow<Profile>

    suspend fun getCachedProfile(): Profile?
}