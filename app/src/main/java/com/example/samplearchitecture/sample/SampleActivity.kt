package com.example.samplearchitecture.sample

import androidx.lifecycle.Observer
import com.example.samplearchitecture.core.android.BaseActivity
import com.example.samplearchitecture.core.android.BindingInflater
import com.example.samplearchitecture.databinding.SampleActivityBinding
import com.google.android.material.snackbar.Snackbar

class SampleActivity : BaseActivity<SampleActivityViewModel, SampleActivityBinding>() {

    override fun getBindingInflater(): BindingInflater = SampleActivityBinding::inflate

    override fun initComponents() {
        binding.button.setOnClickListener {
            viewModel.onClickShowLoading5s()
        }
    }

    override fun initObservers() {
        viewModel.showSnackbarEvent.observe(this, Observer {
            Snackbar.make(binding.root, "Showing Snackbar", Snackbar.LENGTH_LONG)
        })
    }
}