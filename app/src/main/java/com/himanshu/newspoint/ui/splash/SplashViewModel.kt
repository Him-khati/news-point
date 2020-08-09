package com.himanshu.newspoint.ui.splash

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vinners.core.logger.Logger
import com.himanshu.newspoint.BuildConfig
import com.vinners.newspoint.core.SingleLiveEvent
import com.vinners.newspoint.core.locale.LocalisationRepository
import com.vinners.newspoint.data.exceptions.AppVersionDiscontinuedException
import com.vinners.newspoint.data.repository.AppUpdateRepository
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import com.vinners.newspoint.feature.auth.ui.AuthViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

sealed class LauncherActivityState {

    object AppVersionDiscontinued : LauncherActivityState()

    object LocaleNotSelected : LauncherActivityState()

    object UserNotLoggedIn : LauncherActivityState()

    object UserLoggedIn : LauncherActivityState()

    object ShowAppIntro : LauncherActivityState()
}


class SplashViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager,
    private val localisationRepository: LocalisationRepository,
    @Named("session_independent_pref") private val sharedPreferences: SharedPreferences,
    private val appUpdateRepository: AppUpdateRepository,
    private val logger: Logger
) : AuthViewModel(userSessionManager) {

    private val _launcherState = SingleLiveEvent<LauncherActivityState>()
    val launcherState: LiveData<LauncherActivityState> = _launcherState

    fun init() = viewModelScope.launch {

        logger.d("Init...")
        if (shouldCheckForAppUpdate()) {
            logger.d("shouldCheckForAppUpdate() : true, checking for app update....")
            checkForAppUpdate()
        } else {
            checkIfLanguageSelected()
        }
    }

    private suspend fun checkIfNeedToShowAppIntro() {
        val appIntroShown = sharedPreferences.getBoolean(APP_INTRO_SHOWN, false)
        // if (appIntroShown) {
        // logger.d("appIntroShown : true")
        // checkForUserLogin()
        // } else
        _launcherState.postValue(LauncherActivityState.ShowAppIntro)
    }

    private fun shouldCheckForAppUpdate(): Boolean {
        val lastUpdateCheckedAt = sharedPreferences.getLong(LAST_APP_UPDATE_CHECK_TIME, -1)

        if (lastUpdateCheckedAt == -1L)
            return true

        val timeDiff = Date().time - lastUpdateCheckedAt
        val diffInHours = TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS)
        return diffInHours >= FOUR_HOURS
    }

    private suspend fun checkForAppUpdate() = withTimeout(MAX_APP_UPDATE_CHECKING_TIME) {
        try {
            logger.d("Checking for app update.....")
            appUpdateRepository.checkForAppUpdate()

            logger.d("checkForAppUpdate: no updated available")
            sharedPreferences.edit().putLong(LAST_APP_UPDATE_CHECK_TIME, Date().time).apply()

            checkIfLanguageSelected()
        } catch (e: AppVersionDiscontinuedException) {
            logger.d("checkForAppUpdate: App version discontinued [${_root_ide_package_.com.himanshu.newspoint.BuildConfig.VERSION_NAME}]")
            _launcherState.postValue(LauncherActivityState.AppVersionDiscontinued)
        } catch (e: Exception) {
            logger.e(e, "checkForAppUpdate: Error while checking app update")
            checkIfLanguageSelected()
        }
    }

    private suspend fun checkIfLanguageSelected() {

        logger.d("Checking if locale is set...")
        //if (localisationRepository.isLocaleSet()) {
        logger.d("Locale is set to ${localisationRepository.getCurrentLanguage().languageNameShort}")
        checkIfNeedToShowAppIntro()
        //} else {
        // logger.d("Locale is Not set.")
        // _launcherState.postValue(LauncherActivityState.LocaleNotSelected)
        //}
    }

    private suspend fun checkForUserLogin() {
        if (isUserLoggedIn()) {
            logger.d("isUserLoggedIn() : true")
            _launcherState.postValue(LauncherActivityState.UserLoggedIn)
        } else
            _launcherState.postValue(LauncherActivityState.UserNotLoggedIn)
    }

    companion object {
        const val APP_INTRO_SHOWN = "app_intro_shown"
        private const val LAST_APP_UPDATE_CHECK_TIME = "last_app_update_check_time"
        private const val FOUR_HOURS = 4
        private const val MAX_APP_UPDATE_CHECKING_TIME = 20_000L
    }
}