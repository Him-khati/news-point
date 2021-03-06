package com.himanshu.newspoint.feature.auth.ui.forgotPassword

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.vinners.newspoint.core.base.BaseFragment
import com.vinners.newspoint.feature.auth.R
import com.vinners.newspoint.feature.auth.databinding.FragmentForgotPasswordBinding
import com.vinners.newspoint.feature.auth.di.AuthViewModelFactory
import com.himanshu.newspoint.feature.auth.di.DaggerAuthComponent
import javax.inject.Inject

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(R.layout.fragment_forgot_password) {

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    override val viewModel: ForgotPasswordViewModel by viewModels { viewModelFactory }

    override fun onInitDependencyInjection() {
        _root_ide_package_.com.himanshu.newspoint.feature.auth.di.DaggerAuthComponent
            .builder()
            .coreComponent(getCoreComponent())
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onInitViewModel() {
        viewModel
            .sendResetLinkTaskState
            .observe(this, Observer {


            })
    }
}
