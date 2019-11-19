package com.javdiana.freebleticket.view.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.model.entity.Category
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.repository.CategoryRepository
import com.javdiana.freebleticket.view.model.repository.EventRepository
import kotlin.collections.ArrayList

class HomeViewModel(
    private val eventRepository: EventRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val events = MutableLiveData<ArrayList<Event>>()
    val collections = MutableLiveData<ArrayList<Event>>()
    val buttons = MutableLiveData<ArrayList<Category>>()
    val upcomingEvents = MutableLiveData<ArrayList<Event>>()

    fun getListEvents() {
        events.postValue(eventRepository.getEvents())
    }

    fun getListCollections() {
        collections.postValue(eventRepository.getEvents())
    }

    fun getListButtons() {
        buttons.postValue(categoryRepository.getCategories())
    }

    fun deleteEvent(event: Event) {
        events.value?.remove(event)
        eventRepository.deleteEvent(event)
    }

    fun getListUpcomingEvents() {
        upcomingEvents.postValue(eventRepository.getUpcomingEvents())
    }
}