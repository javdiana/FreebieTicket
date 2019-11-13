package com.javdiana.freebleticket.view.view.main

import com.javdiana.freebleticket.view.model.repository.EventRepository
import com.javdiana.freebleticket.view.model.repository.EventRepositoryImpl
import com.javdiana.freebleticket.view.view.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature(): List<Module> = loadFeature

private val loadFeature: List<Module> by lazy {
    listOf(
        viewModelModule,
        viewRepository
    )
}

val viewModelModule: Module = module {
    single<EventRepository> { EventRepositoryImpl() }
}

val viewRepository: Module = module {
    viewModel { HomeViewModel(get()) }
}
