package com.doyoonkim.unsplashpracticerevised.service

import com.doyoonkim.unsplashpracticerevised.data.SearchResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/search/photos?client_id=P9bM_8gbX7t0NVuBbi6tZLKYgNdrdbcZ-rAwpSM4Ke0")
    fun loadImages(
        @Query("query") query: String,
        @Query("page")  page: Int,
        @Query("per_page") perPage: Int,
        @Query("lang") lang: String
    ): Single<SearchResult>     // Utilize RxJava Single. (Publish Single Data.)
}