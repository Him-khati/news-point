package com.himanshu.newspoint

import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.vinners.newspoint.base.AppInfo
import com.vinners.newspoint.core.base.CoreApplication

val DEBUG_APP_INFO = AppInfo(
    debugBuild = true,
    appVersion = _root_ide_package_.com.himanshu.newspoint.BuildConfig.VERSION_NAME,
    packageName = _root_ide_package_.com.himanshu.newspoint.BuildConfig.APPLICATION_ID,
    lastCommit = _root_ide_package_.com.himanshu.newspoint.BuildConfig.GIT_HASH,
    baseApiUrl = _root_ide_package_.com.himanshu.newspoint.BuildConfig.API_URL
)

class TrumanApp : CoreApplication(
    appInfo = DEBUG_APP_INFO
) {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Stetho.initializeWithDefaults(this)
    }
}