package com.himanshu.newspoint.core.di.modules

import android.annotation.SuppressLint
import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.vinners.core.logger.Logger
import com.vinners.newspoint.core.analytics.AnalyticsHelper
import com.vinners.newspoint.core.analytics.AnalyticsHelperImpl
import com.vinners.newspoint.core.logger.LoggerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class CoreModule {

    @Module
    companion object {

        @SuppressLint("MissingPermission")
        @Singleton
        @Provides
        @JvmStatic
        fun provideFirebaseAnalytics(context: Context): FirebaseAnalytics {
            return FirebaseAnalytics.getInstance(context)
        }

        @SuppressLint("MissingPermission")
        @Singleton
        @Provides
        @JvmStatic
        fun provideFirebaseCrashlytics(context: Context): FirebaseCrashlytics {
            return FirebaseCrashlytics.getInstance()
        }
    }

    @Singleton
    @Binds
    abstract fun bindAnalyticsHelper(analyticsHelperImpl: AnalyticsHelperImpl): AnalyticsHelper

    @Singleton
    @Binds
    abstract fun bindLogger(loggerImpl: LoggerImpl): Logger


}