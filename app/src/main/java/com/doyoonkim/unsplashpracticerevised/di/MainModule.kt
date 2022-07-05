package com.doyoonkim.unsplashpracticerevised.di

import com.doyoonkim.unsplashpracticerevised.adapter.ResultListAdapter
import com.doyoonkim.unsplashpracticerevised.data.DataModel
import com.doyoonkim.unsplashpracticerevised.data.DataModelImpl
import com.doyoonkim.unsplashpracticerevised.service.RetrofitService
import com.doyoonkim.unsplashpracticerevised.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// Dependency Injection using Koin.

var modelPart = module {
    // factory: Just as its name, factory where create certain class.
    // factoryOf<DataModel>(::DataModelImpl)   // Kotlin Extended DSL (3.2)
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModelOf(::MainViewModel)    // Kotlin Extended DSL (3.2)
    /*
    viewModel {
        MainViewModel(get())
    }
     */
}

var retrofitPart = module {
    single<RetrofitService> {
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}

var recyclerAdapterPart = module {
    factory {
        ResultListAdapter(get())
    }
}

var mainDiModule = listOf(modelPart, viewModelPart, retrofitPart, recyclerAdapterPart)