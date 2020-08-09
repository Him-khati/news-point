package com.himanshu.newspoint.ui.languageSelection

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.vinners.newspoint.R
import com.vinners.newspoint.core.base.BaseFragment
import com.vinners.newspoint.core.locale.Language
import com.vinners.newspoint.databinding.FragmentSelectLanguageBinding
import com.himanshu.newspoint.di.DaggerLauncherComponent
import com.vinners.newspoint.di.LauncherViewModelFactory
import com.vinners.newspoint.feature.auth.ui.AuthActivity
import javax.inject.Inject

class SelectLanguageFragment :
    BaseFragment<FragmentSelectLanguageBinding, LanguageViewModel>(R.layout.fragment_select_language) {

    @Inject
    lateinit var viewModelFactory: LauncherViewModelFactory

    override val viewModel: LanguageViewModel by viewModels { viewModelFactory }

    override fun onInitDependencyInjection() {
        _root_ide_package_.com.himanshu.newspoint.di.DaggerLauncherComponent
            .builder()
            .coreComponent(getCoreComponent())
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        setListeners()
    }

    override fun onInitViewModel() {
        viewModel.events
            .languageSelectionCompleted
            .observe(viewLifecycleOwner, Observer {
                startLoginActivity()
            })
    }

    private fun setListeners() {
        viewBinding.englishLanguageCard.setOnClickListener {
            viewModel.setLanguage(requireContext(), Language.ENGLISH)
        }

        viewBinding.hindiLanguageCard.setOnClickListener {
            viewModel.setLanguage(requireContext(), Language.HINDI)
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(requireContext(), AuthActivity::class.java)
        startActivity(intent)
    }
}
