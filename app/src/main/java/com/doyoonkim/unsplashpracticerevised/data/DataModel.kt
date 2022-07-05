package com.doyoonkim.unsplashpracticerevised.data

import com.doyoonkim.unsplashpracticerevised.service.RetrofitService
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// Data Model interface.
interface DataModel {
    fun getData()

    // For Retrofit service
    fun searchImage(query: String, page: Int, perPage: Int, lang: String): Single<SearchResult>
}

// Implementation
class DataModelImpl(private val service: RetrofitService): DataModel {
    override fun getData() {
        return
    }

    override fun searchImage(
        query: String,
        page: Int,
        perPage: Int,
        lang: String
    ): Single<SearchResult> {
        return service.loadImages(query, page, perPage, lang)
    }
}