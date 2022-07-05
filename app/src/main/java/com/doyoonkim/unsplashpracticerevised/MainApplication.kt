package com.doyoonkim.unsplashpracticerevised

import android.app.Application
import com.doyoonkim.unsplashpracticerevised.di.mainDiModule
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

// Initial Entry Point of Application
//
// Set name of Application in AndroidManifest as .MainApplication

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Kotlin Android Logger
            androidLogger()
            androidContext(this@MainApplication)
            modules(mainDiModule)
        }
    }
}
