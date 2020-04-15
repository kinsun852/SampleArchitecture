package com.example.samplearchitecture.sample

import com.example.samplearchitecture.core.android.BaseViewModel
import com.example.samplearchitecture.core.livedata.SingleLiveEvent
import io.reactivex.Completable
import java.util.concurrent.TimeUnit.SECONDS

class SampleActivityViewModel : BaseViewModel() {

    val showSnackbarEvent = SingleLiveEvent<Void>()

    fun onClickShowLoading5s() {
        execute {
            Completable.complete()
                .delay(5, SECONDS)
                .doAfterTerminate {
                    showLoadingEvent.postValue(false)
                    showSnackbarEvent.call()
                }
                .doOnSubscribe {
                    showLoadingEvent.postValue(true)
                }.subscribe()
        }
    }
}

