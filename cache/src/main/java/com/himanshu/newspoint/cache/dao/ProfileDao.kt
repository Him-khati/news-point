package com.himanshu.newspoint.cache.dao


import androidx.room.*
import com.vinners.newspoint.cache.entities.CachedProfile
import kotlinx.coroutines.flow.Flow


/**
 * Created by Himanshu on 6/22/2018.
 */
@Dao
interface ProfileDao {

    @get:Query("SELECT * FROM ${CachedProfile.TABLE_NAME} LIMIT 1")
    val profile: Flow<CachedProfile>

    @Query("SELECT * FROM ${CachedProfile.TABLE_NAME} LIMIT 1")
    suspend fun getCachedProfile(): CachedProfile?

    @Query("SELECT ${CachedProfile.COLUMN_PROFILE_PIC} FROM ${CachedProfile.TABLE_NAME}")
    suspend fun getProfilePicFromLocal(): String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cachedProfile: CachedProfile)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(cachedProfile: CachedProfile)

    @Query("UPDATE ${CachedProfile.TABLE_NAME} SET " + "${CachedProfile.COLUMN_PROFILE_PICTURE_UPDATED}=1")
    suspend fun markProfilePictureAsUpdated()

    @Query("UPDATE ${CachedProfile.TABLE_NAME} SET " + "${CachedProfile.COLUMN_RESUME_UPDATED}=1")
    suspend fun markResumeAsUpdated()

    @Query(
        "UPDATE ${CachedProfile.TABLE_NAME} SET " +
                "${CachedProfile.COLUMN_AADHAR_STATUS}=:aadharStatus," +
                "${CachedProfile.COLUMN_PAN_STATUS}=:panStatus"
    )
    suspend fun updateAadharPanResumeStatus(aadharStatus: String?, panStatus: String?)

    @Query(
        "UPDATE ${CachedProfile.TABLE_NAME} SET " +
                "${CachedProfile.COLUMN_NAME}=:name," +
                "${CachedProfile.COLUMN_MOBILE_NUMBER}=:mobileNumber," +
                "${CachedProfile.COLUMN_EMAIL}=:email," +
                "${CachedProfile.COLUMN_CITY_ID}=:cityId," +
                "${CachedProfile.COLUMN_CITY_NAME}=:cityName"
    )
    suspend fun updateProfileData(
        name: String,
        mobileNumber: String,
        cityId: String,
        cityName: String,
        email: String?
    )

}
