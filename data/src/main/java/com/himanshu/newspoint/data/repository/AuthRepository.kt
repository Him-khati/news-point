package com.himanshu.newspoint.data.repository

import com.vinners.newspoint.data.dataStores.AuthRemoteDataStore
import com.vinners.newspoint.data.models.auth.*
import com.vinners.newspoint.data.models.mdm.MobileInformation
import com.vinners.newspoint.data.models.stateAndCity.City
import com.vinners.newspoint.data.models.stateAndCity.Pincode
import com.vinners.newspoint.data.models.stateAndCity.State
import com.vinners.newspoint.data.models.stateAndCity.WorkCategory
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authRemoteDataStore: AuthRemoteDataStore,
    private val userSessionManager: UserSessionManager
) {

    suspend fun getUserRegistrationInfo(
        mobileNumber: String?,
        appHash: String?
    ): UserRegistrationCheckResponse {
        return authRemoteDataStore.getUserRegistrationInfo(
            mobileNumber,
            appHash
        )
    }

    suspend fun login(
        mobileNumber: String?,
        password: String?,
        appHash: String?,
        mobileInformation: MobileInformation?
    ): LoginResponse {
        return authRemoteDataStore.login(mobileNumber, password, appHash, mobileInformation)
    }

    suspend fun loginWithOtp(
        mobileNumber: String?,
        appHash: String?
    ): LoginWithOtpResponse {
        return authRemoteDataStore.loginWithOtp(mobileNumber, appHash)
    }

    suspend fun verifyOtp(
        otpToken: String?,
        otp: String?
    ) {
        authRemoteDataStore.verifyOtp(otpToken, otp)
    }

    suspend fun verifyOtpLogin(
        otp: String?,
        otpToken: String?,
        mobileInformation: MobileInformation?
    ): LoginResponse {
        return authRemoteDataStore.verifyOtpLogin(otp, otpToken, mobileInformation)
    }

    suspend fun resendOtp(
        otpToken: String
    ) {

    }

    suspend fun register(registerRequest: RegisterRequest) {
        authRemoteDataStore.register(registerRequest)
    }

    suspend fun forgotPassword(
        mobileNumber: String?
    ) {
        authRemoteDataStore.forgotPassword(mobileNumber)
    }

    suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ) {

    }

    suspend fun getState(): List<State> {
        return authRemoteDataStore.getState()
    }

    suspend fun getCity(stateId: String?): List<City> {
        return authRemoteDataStore.getCity(stateId)
    }

    suspend fun getPincode(districtId: String?): List<Pincode> {
        return authRemoteDataStore.getPincode(districtId)
    }

    suspend fun getWorkCategory(): List<WorkCategory> {
        return authRemoteDataStore.getWorkCategory()
    }
}