package com.javdiana.freebleticket.view.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.repository.EventRepository

class DetailsViewModel(private val eventRepository: EventRepository) : ViewModel() {
    val event = MutableLiveData<Event>()
    var id: Long = -1

    fun getEvent() {
        event.value = eventRepository.getEvent(id)
    }
}