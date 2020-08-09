package com.himanshu.newspoint.feature.auth.ui

import androidx.lifecycle.ViewModel
import com.vinners.newspoint.data.models.LoggedInUser
import com.vinners.newspoint.data.sessionManagement.UserSessionManager

open class AuthViewModel(
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    suspend fun isUserLoggedIn(): Boolean {
        return userSessionManager.getLoggedInUser() != null
    }

    @Throws(IllegalStateException::class)
    suspend fun getLoggedInUser(): LoggedInUser {
        return userSessionManager.getLoggedInUser()
            ?: throw IllegalStateException("getLoggedInUser() , no logged In User")
    }
}