package com.example.samplearchitecture.core.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.samplearchitecture.core.android.viewbindingutil.viewBinding
import com.example.samplearchitecture.core.extension.android.addFragment
import com.example.samplearchitecture.core.extension.android.replaceFragment
import com.example.samplearchitecture.databinding.BaseActivityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected val viewModel: VM by viewModel(viewModelClass())
    private val baseBinding: BaseActivityBinding by viewBinding(BaseActivityBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNCHECKED_CAST")
        binding = inflate().invoke(layoutInflater, baseBinding.contentContainer, true) as VB

        supportFragmentManager.addOnBackStackChangedListener {
            with(supportFragmentManager) {
                val index = backStackEntryCount - 1

                Timber.d("BackStackEntryCount: $index")
            }
        }

        setContentView(baseBinding.root)
        setSupportActionBar(baseBinding.toolbar)
        initBaseObservers()
        initObservers()
        initComponents()
    }

    abstract fun inflate(): BindingInflater
    abstract fun initObservers()
    abstract fun initComponents()

    private fun initBaseObservers() {
        viewModel.showLoadingEvent.observe(this, Observer {
            baseBinding.loadingContainer.isVisible = it
        })
    }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<VM> {
        return ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>).kotlin
    }

    protected fun showToolbar(show: Boolean = true) {
        baseBinding.toolbar.isVisible = show
    }

    protected fun showBackButton(show: Boolean = true) {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(show)
            it.setDisplayShowHomeEnabled(show)
        }
    }

    private fun getContentContainerId(): Int {
        return baseBinding.contentContainer.id
    }

    fun addFragment(fragment: Fragment) {
        this.addFragment(fragment, getContentContainerId())
    }

    fun replaceFragment(fragment: Fragment) {
        this.replaceFragment(fragment, getContentContainerId())
    }
}

typealias BindingInflater = (LayoutInflater, ViewGroup, Boolean) -> ViewBinding
