package com.example.samplearchitecture.sample

import android.os.Bundle
import android.view.View
import com.example.samplearchitecture.SecondFragment
import com.example.samplearchitecture.core.android.BaseActivity
import com.example.samplearchitecture.core.android.BaseFragment
import com.example.samplearchitecture.core.android.BindInflater
import com.example.samplearchitecture.databinding.FragmentSampleTwoBinding

class SampleFragmentTwo : BaseFragment<FragmentSampleTwoBinding>() {
    override fun inflate(): BindInflater = FragmentSampleTwoBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTwo.setOnClickListener {
            (activity as BaseActivity<*, *>).replaceFragment(SecondFragment())
        }
    }
}