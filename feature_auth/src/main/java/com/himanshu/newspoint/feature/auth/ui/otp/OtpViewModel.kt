package com.himanshu.newspoint.feature.auth.ui.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.iid.FirebaseInstanceId
import com.vinners.core.logger.Logger
import com.vinners.newspoint.core.mdm.DeviceInfoProvider
import com.vinners.newspoint.core.taskState.Lce
import com.vinners.newspoint.core.taskState.Lse
import com.vinners.newspoint.data.models.auth.LoginResponse
import com.vinners.newspoint.data.models.mdm.MobileInformation
import com.vinners.newspoint.data.repository.AuthRepository
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import com.himanshu.newspoint.feature.auth.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface OtpConfirmEvents {
    fun registerSmsReceiver(): LiveData<String>

    fun unregisterSmsReceiver(): LiveData<String>

    fun otpConfirmState(): LiveData<Lse>

    fun otpLoginConfirmState(): LiveData<Lce<LoginResponse>>
}

class OtpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userSessionManager: UserSessionManager,
    private val logger: Logger,
    private val deviceInfoProvider: DeviceInfoProvider
) : ViewModel(), OtpConfirmEvents {

    private var _isReadingOtp = true
    val isReadingOtp: Boolean get() = _isReadingOtp

    private val _registerSmsReceiver = MutableLiveData<String>()
    override fun registerSmsReceiver(): LiveData<String> = _registerSmsReceiver

    private val _unRegisterSmsReceiver = MutableLiveData<String>()
    override fun unregisterSmsReceiver(): LiveData<String> = _unRegisterSmsReceiver

    private val _otpConfirmState = MutableLiveData<Lse>()
    override fun otpConfirmState(): LiveData<Lse> = _otpConfirmState

    private val _otpLoginConfirmState = MutableLiveData<Lce<LoginResponse>>()
    override fun otpLoginConfirmState(): LiveData<Lce<LoginResponse>> = _otpLoginConfirmState

    fun otpConfirm(otpToken: String?, otp: String?) {
        _otpConfirmState.value = Lse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                logger.d("Otp confirmed for register")
                _isReadingOtp = false
                _unRegisterSmsReceiver.postValue("Unregistering Sms receiver...")
                authRepository.verifyOtp(otpToken, otp)
                _otpConfirmState.postValue(Lse.Success)
            } catch (e: Exception) {
                _isReadingOtp = true
                logger.d("registering Sms receiver")
                _registerSmsReceiver.postValue("Registering Sms receiver...")
                _otpConfirmState.postValue(Lse.Error(e.localizedMessage))
            }
        }
    }

    fun otpLoginConfirm(otpToken: String?, otp: String?){
        _otpLoginConfirmState.value = Lce.Loading
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = authRepository.verifyOtpLogin(otp,otpToken,getMobileInformation())
                _otpLoginConfirmState.postValue(Lce.Content(response))
                logger.d("Otp confirmed for login")
            }catch (e: Exception){
                _otpLoginConfirmState.postValue(Lce.Error(e.localizedMessage))
            }
        }
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