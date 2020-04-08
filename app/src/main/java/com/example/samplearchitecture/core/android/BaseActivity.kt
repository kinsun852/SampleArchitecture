package com.example.samplearchitecture.core.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.samplearchitecture.core.android.viewbindingutil.viewBinding
import com.example.samplearchitecture.databinding.BaseActivityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected val viewModel: VM by viewModel(viewModelClass())
    private val baseBinding: BaseActivityBinding by viewBinding(BaseActivityBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        setContentView(baseBinding.root)
        setSupportActionBar(baseBinding.toolbar)
        initBaseObservers()
        initObservers()
        initComponents()
    }

    abstract fun getBindingInflater(): BindingInflater
    abstract fun initObservers()
    abstract fun initComponents()

    private fun initBaseObservers() {
        viewModel.showLoadingEvent.observe(this, Observer {
            baseBinding.loadingContainer.isVisible = it
        })
    }

    @Suppress("UNCHECKED_CAST")
    private fun initBinding() {
        binding = getBindingInflater().invoke(layoutInflater, baseBinding.contentContainer, true) as VB
    }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<VM> {
        return ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>).kotlin
    }
}

typealias BindingInflater = (LayoutInflater, ViewGroup, Boolean) -> ViewBinding
