package com.himanshu.newspoint.feature.auth.ui.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinners.core.logger.Logger
import com.vinners.newspoint.core.TaskState
import com.vinners.newspoint.core.analytics.AnalyticsHelper
import com.vinners.newspoint.data.repository.AuthRepository
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager,
    private val logger: Logger,
    private val analytics: AnalyticsHelper,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _sendResetLinkTaskState = MutableLiveData<TaskState<String>>()
    val sendResetLinkTaskState: LiveData<TaskState<String>> = _sendResetLinkTaskState

    fun sendResetLink(email: String) = viewModelScope.launch {
        try {
            _sendResetLinkTaskState.postValue(TaskState.Success("Password Reset Link Sent"))
        } catch (e: Exception) {
            _sendResetLinkTaskState.postValue(
                TaskState.Error(
                    e.message ?: "Unable to Send Password Reset Link"
                )
            )
        }
    }

}
