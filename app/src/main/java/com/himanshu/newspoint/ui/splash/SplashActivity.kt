package com.himanshu.newspoint.ui.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.vinners.newspoint.R
import com.vinners.newspoint.core.base.BaseActivity
import com.vinners.newspoint.databinding.ActivitySplashBinding
import com.himanshu.newspoint.di.DaggerLauncherComponent
import com.vinners.newspoint.di.LauncherViewModelFactory
import com.vinners.newspoint.ui.appIntro.AppIntroActivity
import javax.inject.Inject


class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(R.layout.activity_splash) {
    @Inject
    lateinit var viewModelFactory: LauncherViewModelFactory

    override val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onInitDependencyInjection() {
        _root_ide_package_.com.himanshu.newspoint.di.DaggerLauncherComponent
            .builder()
            .coreComponent(getCoreComponent())
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {

    }

    override fun onInitViewModel() {
        viewModel.launcherState
            .observe(this, Observer {
                when (it) {
                    // LauncherActivityState.LocaleNotSelected -> startSelectLanguageActivity()
                    // LauncherActivityState.AppVersionDiscontinued -> startAppVersionDiscontinuedActivity()
                    // LauncherActivityState.UserNotLoggedIn -> startLoginActivity()
                    // LauncherActivityState.UserLoggedIn -> startMainActivity()
                    LauncherActivityState.ShowAppIntro -> showAppIntro()
                }
            })

        viewModel.init()
    }
    private fun showAppIntro() {
        android.os.Handler().postDelayed(
            Runnable {
              startActivity(Intent(this,AppIntroActivity::class.java))
            }, 3000
        )

    }

    private fun startAppVersionDiscontinuedActivity() {

    }

    private fun startSelectLanguageActivity() {

    }

    private fun startMainActivity() {

    }

    private fun startLoginActivity() {
//        startActivity(Intent(this, AuthActivity::class.java))
    }
}