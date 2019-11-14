package com.javdiana.freebleticket.view.view.main

import android.app.Application
import com.javdiana.freebleticket.view.view.injectFeature
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppConfigKoin : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@AppConfigKoin)
        }

        injectFeature()

    }

}