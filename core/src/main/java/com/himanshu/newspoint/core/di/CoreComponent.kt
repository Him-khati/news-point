package com.himanshu.newspoint.core.di

import android.app.Application
import android.content.SharedPreferences
import com.google.firebase.analytics.FirebaseAnalytics
import com.vinners.core.logger.Logger
import com.vinners.newspoint.base.AppInfo
import com.vinners.newspoint.core.AppSignatureHelper
import com.vinners.newspoint.core.analytics.AnalyticsHelper
import com.vinners.newspoint.core.di.modules.*
import com.vinners.newspoint.core.locale.LocalisationRepository
import com.vinners.newspoint.core.logger.LoggerImpl
import com.vinners.newspoint.core.mdm.DeviceInfoProvider
import com.vinners.newspoint.data.repository.AppUpdateRepository
import com.vinners.newspoint.data.repository.AuthRepository
import com.vinners.newspoint.data.repository.ProfileRepository
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DataModule::class,
        CoreModule::class,
        ContextModule::class,
        CacheModule::class,
        RemoteModule::class
    ]
)
interface CoreComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun appInfo(appInfo: AppInfo): Builder

        fun build(): CoreComponent
    }

    fun injectApplication(application: Application)

    fun getLoggerImpl(): LoggerImpl

    fun getLogger(): Logger

    fun getAnalyticsHelper(): AnalyticsHelper

    fun getFirebaseAnalytics(): FirebaseAnalytics

    fun getUserSessionManager() : UserSessionManager

    @Named("session_independent_pref")
    fun getSessionIndependentSharedPref() : SharedPreferences

    fun getLocalisationRepository(): LocalisationRepository

    fun getAppUpdateRepository() : AppUpdateRepository

    fun getProfileRepository(): ProfileRepository

    fun getAuthRepository() : AuthRepository

    fun getAppSignature(): AppSignatureHelper

    fun getDeviceInfoProvider() : DeviceInfoProvider

}