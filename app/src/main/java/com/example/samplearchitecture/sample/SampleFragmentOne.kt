package com.example.samplearchitecture.sample

import android.os.Bundle
import android.view.View
import com.example.samplearchitecture.core.android.BaseActivity
import com.example.samplearchitecture.core.android.BaseFragment
import com.example.samplearchitecture.core.android.BindInflater
import com.example.samplearchitecture.databinding.FragmentSampleOneBinding

class SampleFragmentOne : BaseFragment<FragmentSampleOneBinding>() {
    override fun inflate(): BindInflater = FragmentSampleOneBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOne.setOnClickListener {
            (activity as BaseActivity<*, *>).replaceFragment(SampleFragmentTwo())
        }
    }
}