package com.himanshu.newspoint.data.repository

import android.content.SharedPreferences
import com.vinners.core.logger.Logger
import com.vinners.newspoint.data.models.wallet.WalletInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class WalletRepository @Inject constructor(
    private val logger: Logger,
    @Named("session_dependent_pref") private val walletPrefs: SharedPreferences
) {

    fun watchWalletInfo(): Flow<WalletInfo> = flow {

    }

    @Synchronized
    suspend fun addMoney(amount: Double) {

        if (wasLastMoneyOperationBefore5Sec()) {

        } else {
            logger.d("addMoney: two consecutive money operations requested with in 5 Secs. last request time")
            throw IllegalStateException("addMoney: two consecutive money operations cannot be within 5 secs")
        }
    }

    @Synchronized
    fun deductMoney(double: Double) {

        if (wasLastMoneyOperationBefore5Sec()) {

        } else {
            logger.d("deductMoney: two consecutive money operations requested with in 5 Secs. last request time")
            throw IllegalStateException("deductMoney: two consecutive money operations cannot be within 5 secs")
        }
    }

    private fun wasLastMoneyOperationBefore5Sec(): Boolean {
        val lastWalletOperationTime = walletPrefs.getLong(LAST_MONEY_OPERATION_TIME, -1)
        return lastWalletOperationTime == -1L || Date().time - lastWalletOperationTime > FIVE_SECONDS
    }

    companion object {
        private const val LAST_MONEY_OPERATION_TIME = "last_wallet_operation"
        private const val FIVE_SECONDS = 5000
    }
}