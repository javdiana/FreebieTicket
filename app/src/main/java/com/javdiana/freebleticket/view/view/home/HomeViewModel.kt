package com.javdiana.freebleticket.view.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.repository.EventRepository

class HomeViewModel(private val eventRepository: EventRepository) : ViewModel() {
    val events = MutableLiveData<ArrayList<Event>>()

    fun getList() {
        events.postValue(eventRepository.getEvents())
    }
}