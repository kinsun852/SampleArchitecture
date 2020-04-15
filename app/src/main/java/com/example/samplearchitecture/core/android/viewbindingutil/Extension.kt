package com.example.samplearchitecture.core.android.viewbindingutil

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.example.samplearchitecture.core.android.BaseFragment

inline fun <VB : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> VB) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun <VB : ViewBinding> BaseFragment<VB>.viewBinding(viewBindingFactory: (View) -> VB) =
    FragmentViewBindingDelegate(
        this,
        viewBindingFactory
    )