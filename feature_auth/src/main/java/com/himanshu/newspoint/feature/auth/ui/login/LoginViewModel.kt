package com.himanshu.newspoint.feature.auth.ui.login

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.iid.FirebaseInstanceId
import com.vinners.core.logger.Logger
import com.vinners.newspoint.core.AppSignatureHelper
import com.vinners.newspoint.core.ValidationHelper
import com.vinners.newspoint.core.analytics.AnalyticsHelper
import com.vinners.newspoint.core.mdm.DeviceInfoProvider
import com.vinners.newspoint.core.taskState.Lce
import com.vinners.newspoint.core.taskState.Lse
import com.vinners.newspoint.data.models.auth.LoginResponse
import com.vinners.newspoint.data.models.auth.LoginWithOtpResponse
import com.vinners.newspoint.data.models.auth.UserRegistrationCheckResponse
import com.vinners.newspoint.data.models.mdm.MobileInformation
import com.vinners.newspoint.data.repository.AuthRepository
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import com.himanshu.newspoint.feature.auth.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

sealed class UserNameCheckStates {

    data class OtpNotVerified(val otpToken: String) : UserNameCheckStates()

    object RegistrationFormNotCompleted : UserNameCheckStates()

    object ShowPasswordField : UserNameCheckStates()
}


interface LoginViewModelEvents {

    val loginFormState: LiveData<String>

    val loginStateChange: LiveData<Lce<LoginResponse>>

    val registerState: LiveData<Lce<UserNameCheckStates>>

    val loginWithOtpState: LiveData<Lce<LoginWithOtpResponse>>

    val forgetPasswordState: LiveData<Lse>

    val registerWithOtpState: LiveData<Lce<UserRegistrationCheckResponse>>
}

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userSessionManager: UserSessionManager,
    private val appSignatureHelper: AppSignatureHelper,
    private val logger: Logger,
    private val deviceInfoProvider: DeviceInfoProvider,
    private val analytics: AnalyticsHelper
) : ViewModel(), LoginViewModelEvents {


    private val _loginFormState = MutableLiveData<String>()
    override val loginFormState: LiveData<String> get() = _loginFormState

    private val _loginStateChange = MutableLiveData<Lce<LoginResponse>>()
    override val loginStateChange: LiveData<Lce<LoginResponse>> get() = _loginStateChange

    private val _registerState = MutableLiveData<Lce<UserNameCheckStates>>()
    override val registerState: LiveData<Lce<UserNameCheckStates>> get() = _registerState

    private val _loginWithOtpState = MutableLiveData<Lce<LoginWithOtpResponse>>()
    override val loginWithOtpState: LiveData<Lce<LoginWithOtpResponse>> = _loginWithOtpState

    private val _forgetPasswordState = MutableLiveData<Lse>()
    override val forgetPasswordState: LiveData<Lse> = _forgetPasswordState

    private val _registerWithOtpState = MutableLiveData<Lce<UserRegistrationCheckResponse>>()
    override val registerWithOtpState: LiveData<Lce<UserRegistrationCheckResponse>> =
        _registerWithOtpState

    private fun getSignature(): String? {
        return appSignatureHelper
            .appSignatures
            .takeIf { it.isNotEmpty() }
            ?.first()
    }


    fun isUserRegister(mobile: String?) {
        if (!isMobileValid(mobile)) {
            _loginFormState.postValue("Enter Valid Mobile No.")
            return
        }
        _registerWithOtpState.value = Lce.Loading
        logger.d("registering...")
        viewModelScope.launch(Dispatchers.IO) {

            //    _registerState.postValue(Lce.Loading)


            try {
                val registrationInfo =
                    authRepository.getUserRegistrationInfo(mobile, getSignature())
                    _registerWithOtpState.postValue(Lce.Content(registrationInfo))
                logger.d("registered and otp send")
                /* if (registrationInfo.isOtpVerified.not() || registrationInfo.isUserRegistered.not()) {

                //User Not Registered
                val otpToken = registrationInfo.otpToken
                    ?: throw IllegalStateException("isOtpVerified or isUserRegistered is false but otpToken is not provided")
                val state = UserNameCheckStates.OtpNotVerified(otpToken = otpToken)
                _registerState.postValue(Lce.content(state))

                // } else if (registrationInfo.hasUserFilledRegistrationForm) {

                // val state = UserNameCheckStates.RegistrationFormNotCompleted
                // _registerState.postValue(Lce.content(state))
                // } else {

                //Everything's fine
                //  val state = UserNameCheckStates.ShowPasswordField
                //  _registerState.postValue(Lce.content(state))
                // }*/
            } catch (e: Exception) {
                _registerWithOtpState.postValue(Lce.Error(e.localizedMessage))
            }
        }
    }

    fun loginWithOtp(mobile: String?) {
        if (!isMobileValid(mobile)) {
            _loginFormState.postValue("Enter Valid Mobile No.")
            return
        }
        _loginWithOtpState.value = Lce.Loading
        logger.d("Otp sending...")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.loginWithOtp(mobile, getSignature())
                _loginWithOtpState.postValue(Lce.content(response))
                logger.d("Otp sent for login")
            } catch (e: Exception) {
                _loginWithOtpState.postValue(Lce.error(e.localizedMessage))
            }
        }
    }


    fun login(mobile: String?, password: String) {
        if (!isMobileValid(mobile)) {
            _loginFormState.postValue("Enter Valid Mobile No.")
            return
        }
        _loginStateChange.value = Lce.loading()
        viewModelScope.launch {
            try {
                val response =
                    authRepository.login(mobile, password, getSignature(), getMobileInformation())
                logger.d("success", "Login succed")

                _loginStateChange.postValue(Lce.content(response))
            } catch (e: Exception) {
                _loginStateChange.postValue(Lce.error(e.localizedMessage))
                logger.d("failed", e.localizedMessage)
            }
        }
    }

    fun forgetPassword(mobile: String?) {
        if (!isMobileValid(mobile)) {
            _loginFormState.postValue("Enter Valid Mobile No.")
            return
        }
        _forgetPasswordState.value = Lse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                authRepository.forgotPassword(mobile)
                _forgetPasswordState.postValue(Lse.Success)
            } catch (e: Exception) {
                _forgetPasswordState.postValue(Lse.Error(e.localizedMessage))
            }
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(username).matches()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return true
    }

    private fun isMobileValid(mobile: String?): Boolean {
        if (mobile.isNullOrEmpty() || !ValidationHelper.isValidPhoneNumber(mobile!!))
            return false
        else
            return true
    }

    private suspend fun getMobileInformation(): MobileInformation {
        val deviceInfo = try {
            deviceInfoProvider.getDeviceInfo()
        } catch (e: Exception) {
            logger.e(e, "Error While Retreiving Mobile Information")
            null
        }

        if (deviceInfo == null) {
            return MobileInformation(
                deviceInformation = null,
                networkInfo = null,
                osInformation = null,
                deviceId = getFirebaseToken()
            )
        } else {
            deviceInfo.deviceId = getFirebaseToken()
            deviceInfo.appVersion = _root_ide_package_.com.himanshu.newspoint.feature.auth.BuildConfig.VERSION_NAME
            return deviceInfo
        }
    }


    private suspend fun getFirebaseToken(): String? {
        return suspendCoroutine { contination ->
            FirebaseInstanceId
                .getInstance()
                .instanceId
                .addOnSuccessListener {
                    contination.resume(it.token)
                }.addOnFailureListener {
                    logger.e(it, "Error While Fetching Firebase Token")
                    contination.resume(null)
                }
        }
    }
}