package com.himanshu.newspoint.di


import com.vinners.newspoint.core.di.CoreComponent
import com.vinners.newspoint.core.di.scopes.FeatureScope
import com.vinners.newspoint.ui.languageSelection.SelectLanguageFragment
import com.vinners.newspoint.ui.LauncherActivity
import com.vinners.newspoint.ui.appIntro.AppIntroFragment
import com.vinners.newspoint.ui.appIntro.AppIntroActivity
import com.vinners.newspoint.ui.splash.SplashActivity
import com.vinners.newspoint.ui.splash.SplashFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [LauncherModule::class],
    dependencies = [CoreComponent::class]
)
interface LauncherComponent {

    fun inject(launcherActivity: LauncherActivity)

    fun inject(splashActivity: SplashActivity)

    fun inject(appIntroActivity: AppIntroActivity)

    fun inject(splashFragment: SplashFragment)

    fun inject(languageFragment: SelectLanguageFragment)

    fun inject(appIntroFragment: AppIntroFragment)
}