package com.example.samplearchitecture.core.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        @Suppress("UNCHECKED_CAST")
        _binding = inflate().invoke(inflater) as VB
        return _binding!!.root
    }

    abstract fun inflate(): BindInflater

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

typealias BindInflater = (LayoutInflater) -> ViewBinding


