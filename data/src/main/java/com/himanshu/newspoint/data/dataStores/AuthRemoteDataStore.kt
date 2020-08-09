package com.himanshu.newspoint.data.dataStores

import com.vinners.newspoint.data.models.auth.*
import com.vinners.newspoint.data.models.mdm.MobileInformation
import com.vinners.newspoint.data.models.stateAndCity.City
import com.vinners.newspoint.data.models.stateAndCity.Pincode
import com.vinners.newspoint.data.models.stateAndCity.State
import com.vinners.newspoint.data.models.stateAndCity.WorkCategory

interface AuthRemoteDataStore {

    suspend fun getUserRegistrationInfo(
        mobileNumber: String?,
        appHash: String?
    ): UserRegistrationCheckResponse

    suspend fun login(
        mobileNumber: String?,
        password: String?,
        appHash: String?,
        mobileInformation: MobileInformation?
    ): LoginResponse

    suspend fun loginWithOtp(
        mobileNumber: String?,
        appHash: String?
    ): LoginWithOtpResponse


    suspend fun verifyOtp(
        otpToken: String?,
        otp: String?
    )

    suspend fun verifyOtpLogin(otp: String?,otpToken: String?,mobileInformation: MobileInformation?):LoginResponse

    suspend fun resendOtp(
        otpToken: String
    )

    suspend fun register(registerRequest: RegisterRequest)

    suspend fun forgotPassword(
        mobileNumber: String?
    )

    suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    )

    suspend fun getState(): List<State>

    suspend fun getCity(stateId: String?): List<City>

    suspend fun getPincode(districtId: String?): List<Pincode>

    suspend fun getWorkCategory(): List<WorkCategory>
}