package com.himanshu.newspoint.ui.splash

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vinners.newspoint.R
import com.vinners.newspoint.core.base.BaseFragment
import com.vinners.newspoint.core.extensions.navigateSafely
import com.vinners.newspoint.databinding.FragmentSplashBinding
import com.himanshu.newspoint.di.DaggerLauncherComponent
import com.vinners.newspoint.di.LauncherViewModelFactory
import com.vinners.newspoint.feature.auth.ui.AuthActivity
import javax.inject.Inject

class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    @Inject
    lateinit var viewModelFactory: LauncherViewModelFactory

    override val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onInitDataBinding() {
        //Do Nothing
    }

    override fun onInitDependencyInjection() {
        _root_ide_package_.com.himanshu.newspoint.di.DaggerLauncherComponent
            .builder()
            .coreComponent(getCoreComponent())
            .build()
            .inject(this)
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
                findNavController()
                    .navigateSafely(
                        actionId = R.id.action_splashFragment_to_appIntroFragment
                    )
            }, 3000
        )

    }

    private fun startAppVersionDiscontinuedActivity() {
        findNavController()
            .navigateSafely(
                actionId = R.id.action_splashFragment_to_appVersionDiscontinuedFragment
            )
    }

    private fun startSelectLanguageActivity() {
        findNavController().navigateSafely(
            actionId = R.id.selectLanguageFragment
        )
    }

    private fun startMainActivity() {

    }

    private fun startLoginActivity() {
        startActivity(Intent(requireContext(), AuthActivity::class.java))
    }
}
