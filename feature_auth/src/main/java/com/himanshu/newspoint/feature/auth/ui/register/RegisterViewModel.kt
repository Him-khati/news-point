package com.himanshu.newspoint.feature.auth.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vinners.newspoint.core.SingleLiveEvent
import com.vinners.newspoint.core.taskState.Lce
import com.vinners.newspoint.core.taskState.Lse
import com.vinners.newspoint.data.models.auth.RegisterRequest
import com.vinners.newspoint.data.models.stateAndCity.City
import com.vinners.newspoint.data.models.stateAndCity.Pincode
import com.vinners.newspoint.data.models.stateAndCity.State
import com.vinners.newspoint.data.models.stateAndCity.WorkCategory
import com.vinners.newspoint.data.repository.AuthRepository
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import com.vinners.newspoint.feature.auth.ui.AuthViewModel
import com.vinners.newspoint.feature.auth.ui.register.validations.RegisterValidation
import com.vinners.newspoint.feature.auth.ui.register.validations.RegisterValidationException
import com.vinners.newspoint.feature.auth.ui.register.validations.RegisterValidationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

sealed class ValidateState {
    data class ValidateFailed(val result: RegisterValidationResult) : ValidateState()

    object Success : ValidateState()
}

interface RegistrationEvents {
    val registerState: LiveData<Lse>

    val state: LiveData<Lce<List<State>>>

    val city: LiveData<Lce<List<City>>>

    val pincode: LiveData<Lce<List<Pincode>>>

    val workCategory: LiveData<Lce<List<WorkCategory>>>

    val individualBasicValidate: LiveData<ValidateState>

    val agencyBasicsValidate: LiveData<ValidateState>
}

class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userSessionManager: UserSessionManager
) : AuthViewModel(userSessionManager), RegistrationEvents {


    private val _registerState = SingleLiveEvent<Lse>()
    override val registerState: LiveData<Lse> = _registerState

    private val _state = SingleLiveEvent<Lce<List<State>>>()
    override val state: LiveData<Lce<List<State>>> = _state

    private val _city = SingleLiveEvent<Lce<List<City>>>()
    override val city: LiveData<Lce<List<City>>> = _city

    private val _pincode = SingleLiveEvent<Lce<List<Pincode>>>()
    override val pincode: LiveData<Lce<List<Pincode>>> = _pincode

    private val _workCategory = SingleLiveEvent<Lce<List<WorkCategory>>>()
    override val workCategory: LiveData<Lce<List<WorkCategory>>> = _workCategory


    fun register(registerRequest: RegisterRequest) {
        _registerState.value = Lse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                authRepository.register(registerRequest)
                _registerState.postValue(Lse.success())
            } catch (e: Exception) {
                _registerState.postValue(Lse.Error(e.localizedMessage))
            }
        }
    }

    fun getState() {
        _state.value = Lce.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.getState()
                _state.postValue(Lce.Content(response))
            } catch (e: Exception) {
                _state.postValue(Lce.Error(e.localizedMessage))
            }
        }
    }

    fun getCity(stateId: String?) {
        _city.value = Lce.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.getCity(stateId)
                _city.postValue(Lce.Content(response))
            } catch (e: Exception) {
                _city.postValue(Lce.Error(e.localizedMessage))
            }
        }
    }

    fun getPincode(districtId: String?) {
        _pincode.value = Lce.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.getPincode(districtId)
                _pincode.postValue(Lce.Content(response))
            } catch (e: Exception) {
                _pincode.postValue(Lce.Error(e.localizedMessage))
            }
        }
    }

    fun getWorkCategory() {
        _workCategory.value = Lce.loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.getWorkCategory()
                _workCategory.postValue(Lce.Content(response))
            } catch (e: Exception) {
                _workCategory.postValue(Lce.Error(e.localizedMessage))
            }
        }
    }
    //Individual Basics validation fields

    private val _individualBasicsValidate = SingleLiveEvent<ValidateState>()
    override val individualBasicValidate: LiveData<ValidateState> = _individualBasicsValidate

    fun validateIndvidualBasics(firstName: String,email: String,dateOfBirth: Date?) {
        try {
            RegisterValidation.validateIndividualbasics(firstName,email,dateOfBirth)
            _individualBasicsValidate.postValue(ValidateState.Success)
        } catch (e: RegisterValidationException) {
            _individualBasicsValidate.postValue(ValidateState.ValidateFailed(e.result))
        }
    }

    //Agency Basics Validation Fields

    private val _agencyBasicsValidate = SingleLiveEvent<ValidateState>()
    override val agencyBasicsValidate: LiveData<ValidateState> = _agencyBasicsValidate

    fun validateAgencyBasics(agencyName: String,firstName: String,email: String){
        try {
            RegisterValidation.validateAgencyBasics(agencyName,firstName,email)
            _agencyBasicsValidate.postValue(ValidateState.Success)
        }catch (e: RegisterValidationException){
            _agencyBasicsValidate.postValue(ValidateState.ValidateFailed(e.result))
        }
    }

}
