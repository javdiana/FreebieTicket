package com.javdiana.freebleticket.view.view

import com.javdiana.freebleticket.view.model.repository.CustomButtonRepository
import com.javdiana.freebleticket.view.model.repository.CustomButtonRepositoryImp
import com.javdiana.freebleticket.view.model.repository.EventRepository
import com.javdiana.freebleticket.view.model.repository.EventRepositoryImpl
import com.javdiana.freebleticket.view.view.home.HomeViewModel
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
    single<EventRepository> { EventRepositoryImpl() }
    single<CustomButtonRepository> { CustomButtonRepositoryImp() }
}

val repositoryModule: Module = module {
    viewModel { HomeViewModel(get(), get()) }
}
