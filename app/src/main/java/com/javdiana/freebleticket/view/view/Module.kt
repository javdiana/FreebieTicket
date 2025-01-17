package com.javdiana.freebleticket.view.view

import com.javdiana.freebleticket.view.model.repository.CategoryRepository
import com.javdiana.freebleticket.view.model.repository.CategoryRepositoryImpl
import com.javdiana.freebleticket.view.model.repository.EventRepository
import com.javdiana.freebleticket.view.model.repository.EventRepositoryImpl
import com.javdiana.freebleticket.view.view.details.DetailsViewModel
import com.javdiana.freebleticket.view.view.home.HomeViewModel
import com.javdiana.freebleticket.view.view.map.MapViewModel
import com.javdiana.freebleticket.view.view.tickets.TicketsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            repositoryModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { TicketsViewModel(get()) }
    viewModel { MapViewModel(get(), get()) }
}

val repositoryModule: Module = module {
    single<EventRepository> { EventRepositoryImpl() }
    single<CategoryRepository> { CategoryRepositoryImpl() }
}
