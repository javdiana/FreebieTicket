package com.javdiana.freebleticket.view.view.home

import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.model.repository.FestivalRepository

class HomeViewModel(private val festivalRepository: FestivalRepository) : ViewModel() {
    fun getList() = festivalRepository.getFestivals()
}