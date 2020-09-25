package com.example.treerecyclerview.app

import android.app.Application
import com.example.treerecyclerview.di.networkModule
import com.example.treerecyclerview.di.treeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    networkModule,
                    treeModule
                )
            )
        }
    }
}