package com.javdiana.freebleticket.view.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.extensions.getNextDateToLong
import com.javdiana.freebleticket.view.model.entity.Category
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.repository.CategoryRepository
import com.javdiana.freebleticket.view.model.repository.EventRepository
import java.util.*

class HomeViewModel(
    private val eventRepository: EventRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val events = MutableLiveData<ArrayList<Event>>()
    val collections = MutableLiveData<ArrayList<Event>>()
    val categories = MutableLiveData<ArrayList<Category>>()
    val upcomingEvents = MutableLiveData<ArrayList<Event>>()

    fun getListEvents() {
        events.postValue(eventRepository.getEvents())
    }

    fun getListCollections() {
        collections.postValue(eventRepository.getEvents())
    }

    fun getListCategories() {
        categories.postValue(categoryRepository.getCategories())
    }

    fun deleteEvent(event: Event) {
        val ev = events.value
        ev?.remove(event)
        eventRepository.deleteEvent(event)
        events.value = ev
    }


    fun getListUpcomingEvents() {

        upcomingEvents.postValue(eventRepository.getUpcomingEvents())
        upcomingEvents.value?.let {
            it.filter { event -> event.date < 1.getNextDateToLong() && event.date > 3.getNextDateToLong() }
                .map { e -> upcomingEvents.value?.remove(e) }
        }
    }
}
