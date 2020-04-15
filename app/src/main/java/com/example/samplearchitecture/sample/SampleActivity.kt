package com.example.samplearchitecture.sample

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.example.samplearchitecture.R
import com.example.samplearchitecture.core.android.BaseActivity
import com.example.samplearchitecture.core.android.BindingInflater
import com.example.samplearchitecture.databinding.BaseContentBinding
import com.example.samplearchitecture.databinding.SampleActivityBinding
import com.google.android.material.snackbar.Snackbar

class SampleActivity : BaseActivity<SampleActivityViewModel, SampleActivityBinding>() {

    override fun inflate(): BindingInflater = BaseContentBinding::inflate

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_daynight, menu)
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initComponents() {
        title = "Sample Activity"

        replaceFragment(SampleFragmentIntro())

//        binding.buttonLoading.setOnClickListener {
//            viewModel.onClickShowLoading5s()
//        }
    }

    override fun initObservers() {
        viewModel.showSnackbarEvent.observe(this, Observer {

            Snackbar.make(binding.root, "Showing Snackbar", Snackbar.LENGTH_LONG).show()
        })
    }
}