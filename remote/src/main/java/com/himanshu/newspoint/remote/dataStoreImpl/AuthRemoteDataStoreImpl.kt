package com.himanshu.newspoint.remote.dataStoreImpl

import com.vinners.newspoint.data.dataStores.AuthRemoteDataStore
import com.vinners.newspoint.data.models.auth.*
import com.vinners.newspoint.data.models.mdm.MobileInformation
import com.vinners.newspoint.data.models.otp.VerifyOtpLoginRequest
import com.vinners.newspoint.data.models.otp.VerifyOtpRequest
import com.vinners.newspoint.data.models.password.ChangePasswordRequest
import com.vinners.newspoint.data.models.stateAndCity.City
import com.vinners.newspoint.data.models.stateAndCity.Pincode
import com.vinners.newspoint.data.models.stateAndCity.State
import com.vinners.newspoint.data.models.stateAndCity.WorkCategory
import com.himanshu.newspoint.remote.extensions.bodyOrThrow
import com.himanshu.newspoint.remote.models.CheckUserRegistrationStatusRequest
import com.himanshu.newspoint.remote.retrofitServices.AuthService
import javax.inject.Inject

class AuthRemoteDataStoreImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataStore {

    override suspend fun getUserRegistrationInfo(
        mobileNumber: String?,
        appHash: String?
    ): UserRegistrationCheckResponse {
        return authService.checkUserRegistrationStatus(
            CheckUserRegistrationStatusRequest(
                mobile = mobileNumber,
                appHash = appHash
            )
        ).bodyOrThrow().first()
    }

    override suspend fun login(
        mobileNumber: String?,
        password: String?,
        appHash : String?,
        mobileInformation: MobileInformation?
    ): LoginResponse {
        return authService.login(
            LoginRequest(
                userName = mobileNumber,
                password = password,
                appHash = appHash,
                mobileInfo = mobileInformation
            )
        ).bodyOrThrow().first()
    }


    override suspend fun loginWithOtp(mobileNumber: String?, appHash: String?) : LoginWithOtpResponse {
       return authService.loginWithOtp(
            LoginWithOtpRequest(
                mobile = mobileNumber,
                appHash = appHash
            )
        ).bodyOrThrow().first()
    }

    override suspend fun verifyOtp(otpToken: String?, otp: String?) {
        authService.verifyOtp(
            VerifyOtpRequest(
                otpToken = otpToken,
                otp = otp
            )
        ).bodyOrThrow()
    }

    override suspend fun verifyOtpLogin(
        otp: String?,
        otpToken: String?,
        mobileInformation: MobileInformation?
    ): LoginResponse {
       return authService.verifyOtpLogin(VerifyOtpLoginRequest(
            otpToken = otpToken,
            otp = otp,
            mobileInfo = mobileInformation
        )).bodyOrThrow().first()
    }

    override suspend fun resendOtp(otpToken: String) {
        authService.resendOtp(otpToken).bodyOrThrow()
    }

    override suspend fun register(registerRequest: RegisterRequest) {
       authService.register(registerRequest).bodyOrThrow().first()
    }

    override suspend fun forgotPassword(mobileNumber: String?) {
        authService.forgotPassword(
           mobileNumber
        ).bodyOrThrow()
    }

    override suspend fun changePassword(oldPassword: String, newPassword: String) {
        authService.changePassword(
            ChangePasswordRequest(
                oldPassword = oldPassword,
                newPassword = newPassword
            )
        ).bodyOrThrow()
    }

    override suspend fun getState(): List<State> {
        return authService.getState().bodyOrThrow()
    }

    override suspend fun getCity(stateId: String?): List<City> {
        return authService.getCity(stateId).bodyOrThrow()
    }

    override suspend fun getPincode(districtId: String?): List<Pincode> {
        return authService.getPincode(districtId).bodyOrThrow()
    }

    override suspend fun getWorkCategory(): List<WorkCategory> {
        return authService.getWorkCategory().bodyOrThrow()
    }
}