package com.javdiana.freebleticket.view.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.model.entity.Category
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.repository.CategoryRepository
import com.javdiana.freebleticket.view.model.repository.EventRepository

class SearchViewModel(
    private val eventRepository: EventRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val events = MutableLiveData<ArrayList<Event>>()
    val categories = MutableLiveData<ArrayList<Category>>()

    fun getEvents() {
        events.postValue(eventRepository.getEvents())
    }

    fun getCategories() {
        categories.postValue(categoryRepository.getPopularCategories())
    }
}