package com.himanshu.newspoint.feature.wallet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class WalletInfoViewModel : ViewModel() {

    val walletInfo :LiveData<String> = liveData {
        emit("s")
    }

}