package com.himanshu.newspoint.data.repository

import android.content.SharedPreferences
import com.vinners.core.logger.Logger
import com.vinners.newspoint.data.dataStores.userProfile.UserProfileLocalDataStore
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val logger: Logger,
    @Named("session_dependent_pref") private val profilePrefs: SharedPreferences,
    private val userProfileLocalDataStore: UserProfileLocalDataStore
) {

    /**
     * Return A [Flow]
     */
    fun watchWalletInfo() = userProfileLocalDataStore.getProfile()

    companion object {
        private const val LAST_MONEY_OPERATION_TIME = "last_wallet_operation"
        private const val FIVE_SECONDS = 5000
    }
}