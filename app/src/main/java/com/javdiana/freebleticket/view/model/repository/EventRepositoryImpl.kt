package com.javdiana.freebleticket.view.model.repository

import android.annotation.TargetApi
import android.os.Build
import com.javdiana.freebleticket.view.model.entity.Event

class EventRepositoryImpl : EventRepository {
    @TargetApi(Build.VERSION_CODES.O)
    companion object {
        val events: ArrayList<Event> by lazy {
            arrayListOf(
                Event(0, "Event 1", null, "Plasa hotel", 40, 60),
                Event(1, "Event 2", null, "Omega hotel", 60, 80),
                Event(2, "Event 3", null, "Tourist hotel", 50, 70)
            )
        }
    }


    override fun getEvents(): ArrayList<Event> {
        return events
    }
}