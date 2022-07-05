package com.doyoonkim.unsplashpracticerevised.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

// Base class for ViewModel

open class BaseViewModel: ViewModel() {

    // For Observing task. (onSubscribe returns Disposable object, which should be disposed when it
    // is no longer in used.)
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(d: Disposable) {
        compositeDisposable.add(d)
    }

    // Lifecycle function, which is called after the onDestroy() of view is called.
    // Prevent Memory leak.
    override fun onCleared() {
        compositeDisposable.clear()     // Dispose all disposable objects.
        super.onCleared()
    }

}