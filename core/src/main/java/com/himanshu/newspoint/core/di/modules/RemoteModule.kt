package com.himanshu.newspoint.core.di.modules

import android.content.Context
import com.vinners.core.logger.Logger
import com.vinners.newspoint.base.AppInfo
import com.vinners.newspoint.core.SessionExpirationListenerImpl
import com.vinners.newspoint.data.dataStores.AuthRemoteDataStore
import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import com.himanshu.newspoint.remote.RetrofitServiceFactory
import com.himanshu.newspoint.remote.dataStoreImpl.AuthRemoteDataStoreImpl
import com.himanshu.newspoint.remote.networkInterceptors.NetworkCallInterceptor
import com.himanshu.newspoint.remote.retrofitServices.AuthService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
abstract class RemoteModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideAuthService(
            retrofitServiceFactory: RetrofitServiceFactory
        ): AuthService {
            return retrofitServiceFactory.prepareService(AuthService::class.java)
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideRetrofitFactory(
            appInfo: AppInfo,
            networkCallInterceptor: NetworkCallInterceptor
        ): RetrofitServiceFactory {
            return RetrofitServiceFactory(
                appInfo,
                networkCallInterceptor
            )
        }


        @JvmStatic
        @Provides
        @Singleton
        fun provideNetworkCallInterceptor(
            appContext: Context,
            logger: Logger,
            appInfo: AppInfo,
            userSessionManager: UserSessionManager
        ): NetworkCallInterceptor {

            /** Headers Common for All Request*/
            val headers = mapOf(
                "AuthToken" to userSessionManager.sessionToken,
                "Source" to "android",
                "Version" to appInfo.appVersion,
                "LastCommit" to appInfo.lastCommit
            )

            return NetworkCallInterceptor(
                logger
            )
                .setDefaultHeaders(headers)
                .setSessionExpirationListener(
                    SessionExpirationListenerImpl(
                        appContext,
                        userSessionManager
                    )
                )
        }
    }

    @Binds
    abstract fun bindAuthRemoteDataSource(authRemoteDataStoreImpl: AuthRemoteDataStoreImpl): AuthRemoteDataStore

}