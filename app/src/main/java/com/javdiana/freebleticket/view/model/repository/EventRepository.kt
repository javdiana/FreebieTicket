package com.javdiana.freebleticket.view.model.repository

import com.javdiana.freebleticket.view.model.entity.Event

interface EventRepository {
    fun getEvents(): ArrayList<Event>;
}