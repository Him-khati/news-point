package com.himanshu.newspoint.data.sessionManagement

import android.content.SharedPreferences
import com.vinners.newspoint.data.models.LoggedInUser
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserSessionManager @Inject constructor(
    @Named("session_dependent_pref") private val sharedPreferences: SharedPreferences
) {

    val sessionToken: String? = ""

    /**
     * Logs Out
     */
    suspend fun logOut() {
    }

    suspend fun userLoggedIn(): Boolean {
        return true
    }

    suspend fun getLoggedInUser(): LoggedInUser? {
        return null
    }

    suspend fun startNewSession(loggedInUserInfo: LoggedInUser) {

    }

    suspend fun logout() {

    }
}