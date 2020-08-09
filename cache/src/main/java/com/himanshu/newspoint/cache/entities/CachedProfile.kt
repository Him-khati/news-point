package com.himanshu.newspoint.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = CachedProfile.TABLE_NAME)
data class CachedProfile(

    @ColumnInfo(name = COLUMN_NAME)
    var name: String,

//    @ColumnInfo(name = COLUMN_GENDER)
//    var gender: Gender? = null,

    @ColumnInfo(name = COLUMN_MOBILE_NUMBER)
    var mobileNumber: String,

    @ColumnInfo(name = COLUMN_CITY_ID)
    var cityId: String,

    @ColumnInfo(name = COLUMN_CITY_NAME)
    var cityName: String,

    @ColumnInfo(name = COLUMN_PROFILE_PICTURE_UPDATED)
    var profilePicUpdated: Boolean = false,

    @ColumnInfo(name = COLUMN_RESUME_UPDATED)
    var resumeUpdated: Boolean = false,

    @ColumnInfo(name = COLUMN_EMAIL)
    var email: String? = null,

    @ColumnInfo(name = COLUMN_PIN_CODE)
    var pinCode: Int? = null,

    @ColumnInfo(name = COLUMN_DATE_OF_BIRTH)
    var dateOfBirth: Date? = null,

    @ColumnInfo(name = COLUMN_ADDRESS)
    var fullAddress: String? = null,

//    @ColumnInfo(name = COLUMN_EDUCATION)
//    var education: Education? = null,

    @ColumnInfo(name = COLUMN_DESIGNATION_NAME)
    var designationName: String? = null,

    @ColumnInfo(name = COLUMN_DESIGNATION_ID)
    var designationId: String? = null,

    @ColumnInfo(name = COLUMN_EXPERIENCE_MONTHS)
    var experienceInMonths: Int? = null,

    @ColumnInfo(name = COLUMN_CTC)
    var currentCtc: Double? = null,

    @ColumnInfo(name = COLUMN_WORKING_CITY_ID)
    var workingCityId: String? = null,

    @ColumnInfo(name = COLUMN_WORKING_CITY_NAME)
    var workingCityName: String? = null,

    @ColumnInfo(name = COLUMN_AADHAR_STATUS)
    var aadharStatus: String? = null,

    @ColumnInfo(name = COLUMN_PAN_STATUS)
    var panStatus: String? = null,

    @ColumnInfo(name = COLUMN_RESUME_STATUS)
    var resumeStatus: String? = null,

    @ColumnInfo(name = COLUMN_PROFILE_PIC)
    var profilePic: String? = null

) {

    companion object {
        @Ignore
        const val TABLE_NAME: String = "profile"

        @Ignore
        const val COLUMN_PROFILE_PICTURE_UPDATED: String = "profile_pic_updated"

        @Ignore
        const val COLUMN_RESUME_UPDATED: String = "resume_updated"

        @Ignore
        const val COLUMN_NAME: String = "name"

        @Ignore
        const val COLUMN_MOBILE_NUMBER: String = "mobile_number"

        @Ignore
        const val COLUMN_GENDER: String = "gender"

        @Ignore
        const val COLUMN_CITY_ID: String = "city_id"

        @Ignore
        const val COLUMN_CITY_NAME: String = "city_name"


        @Ignore
        const val COLUMN_EMAIL: String = "email"

        @Ignore
        const val COLUMN_PIN_CODE: String = "pin_code"

        @Ignore
        const val COLUMN_DATE_OF_BIRTH: String = "dob"

        @Ignore
        const val COLUMN_ADDRESS: String = "address"

        @Ignore
        const val COLUMN_EDUCATION: String = "education"

        @Ignore
        const val COLUMN_CTC: String = "ctc"


        @Ignore
        const val COLUMN_WORKING_CITY_ID: String = "working_city_id"

        @Ignore
        const val COLUMN_WORKING_CITY_NAME: String = "working_city_name"

        @Ignore
        const val COLUMN_EXPERIENCE_MONTHS: String = "experience_months"

        @Ignore
        const val COLUMN_DESIGNATION_NAME: String = "designationName"

        @Ignore
        const val COLUMN_DESIGNATION_ID: String = "designationId"

        @Ignore
        const val COLUMN_AADHAR_STATUS: String = "aadharStatus"

        @Ignore
        const val COLUMN_PAN_STATUS: String = "panStatus"

        @Ignore
        const val COLUMN_RESUME_STATUS: String = "resumeStatus"

        @Ignore
        const val COLUMN_PROFILE_PIC: String = "profilePic"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

}