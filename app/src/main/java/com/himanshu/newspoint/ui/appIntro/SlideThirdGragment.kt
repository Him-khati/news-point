package com.himanshu.newspoint.ui.appIntro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.api.load
import com.vinners.newspoint.R


class SlideThirdGragment : Fragment() {
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var slideImage: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.slide_fragment_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    fun initView(view: View) {
        title = view.findViewById(R.id.title)
        description = view.findViewById(R.id.descprition)
        slideImage = view.findViewById(R.id.slideImg)
        title.text = getString(R.string.slide_3_title)
        description.text = getString(R.string.slide_3_des)
        slideImage.load(R.drawable.info_slide_img_3)
    }
}