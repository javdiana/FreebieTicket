package com.javdiana.freebleticket.view.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.model.entity.CustomButton
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.repository.CustomButtonRepository
import com.javdiana.freebleticket.view.model.repository.EventRepository

class HomeViewModel(
    private val eventRepository: EventRepository,
    private val customButtonRepository: CustomButtonRepository) : ViewModel() {
    val events = MutableLiveData<ArrayList<Event>>()
    val collections = MutableLiveData<ArrayList<Event>>()
    val buttons = MutableLiveData<ArrayList<CustomButton>>()

    fun getListEvents() {
        events.postValue(eventRepository.getEvents())
    }

    fun getListCollections() {
        collections.postValue(eventRepository.getEvents())
    }

    fun getListButtons(){
        buttons.postValue(customButtonRepository.getDiscoverButtons())
    }
}