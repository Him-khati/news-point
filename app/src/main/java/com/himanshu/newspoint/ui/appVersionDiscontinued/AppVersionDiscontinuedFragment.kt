package com.himanshu.newspoint.ui.appVersionDiscontinued

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.himanshu.newspoint.BuildConfig
import com.vinners.newspoint.R
import com.vinners.newspoint.databinding.FragmentAppVersionDiscontinuedBinding


class AppVersionDiscontinuedFragment : Fragment() {

    private var viewBinding: FragmentAppVersionDiscontinuedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_app_version_discontinued,
            container,
            false
        )
        viewBinding?.lifecycleOwner = viewLifecycleOwner
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.currentVersionTextView?.text =
            getString(R.string.curren_version, _root_ide_package_.com.himanshu.newspoint.BuildConfig.VERSION_NAME)
        viewBinding?.updateAppBtn?.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW)
            i.data =
                Uri.parse("https://play.google.com/store/apps/details?id=${_root_ide_package_.com.himanshu.newspoint.BuildConfig.APPLICATION_ID}")
            startActivity(i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

}