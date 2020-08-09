package com.himanshu.newspoint.feature.auth.di

import com.vinners.newspoint.core.di.CoreComponent
import com.vinners.newspoint.core.di.scopes.FeatureScope
import com.vinners.newspoint.feature.auth.ui.AuthActivity
import com.vinners.newspoint.feature.auth.ui.dashboard.DashBoardActivity
import com.vinners.newspoint.feature.auth.ui.dashboard.DashboardFragment
import com.vinners.newspoint.feature.auth.ui.forgotPassword.ForgotPasswordFragment
import com.vinners.newspoint.feature.auth.ui.login.LoginActivity
import com.vinners.newspoint.feature.auth.ui.login.LoginFragment
import com.vinners.newspoint.feature.auth.ui.otp.OtpActivity
import com.vinners.newspoint.feature.auth.ui.otp.OtpConfirmFragment
import com.vinners.newspoint.feature.auth.ui.register.RegisterFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [
        AuthModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface AuthComponent {

    fun inject(fragment: LoginFragment)

    fun inject(fragment: RegisterFragment)

    fun inject(fragment: ForgotPasswordFragment)

    fun inject(activity: AuthActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: OtpActivity)

    fun inject(activity: DashBoardActivity)

    fun inject(fragment: OtpConfirmFragment)

    fun inject(fragment: DashboardFragment)
}