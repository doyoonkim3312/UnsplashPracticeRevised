package com.doyoonkim.unsplashpracticerevised.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doyoonkim.unsplashpracticerevised.data.DataModel
import com.doyoonkim.unsplashpracticerevised.data.SearchResult
import com.doyoonkim.unsplashpracticerevised.ui.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val model: DataModel): BaseViewModel() {

    // LiveData for search Result
    // _imageSearchResultLiveData => Able to change.
    // imageSearchResultLiveData => Immutable. Able to observe only.
    // Prevent abnormal modification of LiveData from outside of the VM
    private val _imageSearchResultLiveData = MutableLiveData<SearchResult>()
    val imageSearchResultLiveData: LiveData<SearchResult>
    get() = _imageSearchResultLiveData

    // retrofit service
    fun loadImageService(query: String, page: Int, perPage: Int, lang: String) {
        addDisposable(
            model.searchImage(query, page, perPage, lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        it.run {
                            Log.d("MainVM/", "$this")
                            if (total > 0) _imageSearchResultLiveData.postValue(this)
                            // postValue() -> Post a setting value to UI thread.
                        }
                    },
                    {
                        Log.d("Err/", "Unable to retrieve data from server.")
                    }
                )
        )
    }

    /*
        Note:
        subscribeOn(): Declare a thread(scheduler) where the data would be published. This case,
        retrofit service return data from outside. Therefore, io scheduler is suitable.
        observeOn(): Declare a thread(scheduler) where the published data would be observed. This
        case, observed data would be used for a items of recycler view, therefore, main(UI) thread
        is suitable.
     */
}