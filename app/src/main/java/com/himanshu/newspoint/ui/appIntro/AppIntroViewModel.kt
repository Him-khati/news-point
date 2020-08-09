package com.himanshu.newspoint.ui.appIntro

import android.content.SharedPreferences
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import com.vinners.newspoint.feature.auth.ui.AuthViewModel
import com.vinners.newspoint.ui.splash.SplashViewModel
import javax.inject.Inject
import javax.inject.Named

class AppIntroViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager,
    @Named("session_independent_pref") private val sharedPreferences: SharedPreferences
) : AuthViewModel(userSessionManager) {

    fun getLaunchState() {
        if (!sharedPreferences.getBoolean(SplashViewModel.APP_INTRO_SHOWN, false))
            sharedPreferences.edit().putBoolean(SplashViewModel.APP_INTRO_SHOWN, true).apply()
    }
}