package com.javdiana.freebleticket.view.view.main

import android.app.Application
import com.javdiana.freebleticket.view.model.repository.EventRepository
import com.javdiana.freebleticket.view.model.repository.EventRepositoryImpl
import com.javdiana.freebleticket.view.view.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single<EventRepository> { EventRepositoryImpl() }
            viewModel { HomeViewModel(get()) }
        }

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule)
        }

    }

}