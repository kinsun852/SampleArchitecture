package com.example.samplearchitecture.core.di

import com.example.samplearchitecture.sample.SampleActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SampleActivityViewModel() }
}