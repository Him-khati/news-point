package com.himanshu.newspoint.feature.auth.ui.dashboard

import androidx.activity.viewModels
import com.vinners.newspoint.core.base.BaseActivity
import com.vinners.newspoint.core.base.CoreApplication
import com.vinners.newspoint.feature.auth.R
import com.vinners.newspoint.feature.auth.databinding.ActivityDashBoardBinding
import com.vinners.newspoint.feature.auth.di.AuthViewModelFactory
import com.himanshu.newspoint.feature.auth.di.DaggerAuthComponent
import javax.inject.Inject

class DashBoardActivity : BaseActivity<ActivityDashBoardBinding,DashboardViewModel>(R.layout.activity_dash_board) {

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    companion object {
        const val INTENT_USER_NAME = "firstName"
        const val INTENT_MOBILE_NO = "mobile"
    }

    private var firstName: String? = null

   override val viewModel: DashboardViewModel by viewModels {
        viewModelFactory
    }

    override fun onInitDependencyInjection() {
        val coreComponent = (application as CoreApplication).initOrGetCoreDependency()
        _root_ide_package_.com.himanshu.newspoint.feature.auth.di.DaggerAuthComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
    }

    override fun onInitDataBinding() {
        firstName = intent?.getStringExtra(INTENT_USER_NAME)
        viewBinding.mobileEt.text = firstName
        viewBinding.logOutBtn.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onInitViewModel() {

    }

}