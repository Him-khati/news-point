package com.himanshu.newspoint.data.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppUpdateRepository @Inject constructor(
) {

    suspend fun checkForAppUpdate(){
      //  throw AppVersionDiscontinuedException()
    }

}