package com.himanshu.newspoint.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinners.newspoint.core.di.modules.ViewModelKey
import com.vinners.newspoint.ui.appIntro.AppIntroViewModel
import com.vinners.newspoint.ui.languageSelection.LanguageViewModel
import com.vinners.newspoint.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LauncherModule {

    @Binds
    abstract fun bindViewModelFactory(factoryAuth: LauncherViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindLauncherViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LanguageViewModel::class)
    abstract fun bindLanguageViewModel(viewModel: LanguageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppIntroViewModel::class)
    abstract fun bindAppIntroViewModel(viewModel: AppIntroViewModel): ViewModel

}