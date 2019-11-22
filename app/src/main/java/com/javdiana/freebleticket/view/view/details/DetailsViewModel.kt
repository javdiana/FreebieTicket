package com.javdiana.freebleticket.view.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.repository.EventRepository

class DetailsViewModel(private val eventRepository: EventRepository) : ViewModel() {
    val event = MutableLiveData<Event>()
    val additionalEvents = MutableLiveData<ArrayList<Event>>()

    fun getEvent(id: Long) {
        event.postValue(eventRepository.getEvent(id))
    }

    fun getAdditionallEvents() {
        additionalEvents.postValue(eventRepository.getEvents())
    }
}