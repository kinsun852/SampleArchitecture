package com.example.samplearchitecture.core.android

import androidx.lifecycle.ViewModel
import com.example.samplearchitecture.core.livedata.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    val showLoadingEvent = SingleLiveEvent<Boolean>()

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun execute(task: () -> Disposable) {
        disposable.add(task())
    }
}