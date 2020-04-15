package com.example.samplearchitecture.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.samplearchitecture.R
import com.example.samplearchitecture.core.android.BaseActivity
import com.example.samplearchitecture.core.android.BaseFragment
import com.example.samplearchitecture.core.android.BindInflater
import com.example.samplearchitecture.databinding.FragmentFirstBinding
import com.example.samplearchitecture.databinding.SampleActivityBinding

class SampleFragmentIntro : BaseFragment<FragmentFirstBinding>() {

    override fun inflate(): BindInflater = FragmentFirstBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            (activity as BaseActivity<*,*>).replaceFragment(SampleFragmentOne())
        }
    }

}