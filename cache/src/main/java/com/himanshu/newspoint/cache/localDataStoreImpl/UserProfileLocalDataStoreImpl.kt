package com.himanshu.newspoint.cache.localDataStoreImpl

import com.vinners.newspoint.cache.dao.ProfileDao
import com.vinners.newspoint.cache.entityMappers.ProfileEntityMapper
import com.vinners.newspoint.data.dataStores.userProfile.UserProfileLocalDataStore
import com.vinners.newspoint.data.models.profile.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserProfileLocalDataStoreImpl @Inject constructor(
    private val profileDao: ProfileDao,
    private val profileEntityMapper: ProfileEntityMapper
) : UserProfileLocalDataStore {

    override fun getProfile(): Flow<Profile> {
        return profileDao
            .profile
            .map {
                profileEntityMapper.mapFromCached(it)
            }
    }

    override suspend fun getCachedProfile(): Profile? {
        TODO("Not yet implemented")
    }
}