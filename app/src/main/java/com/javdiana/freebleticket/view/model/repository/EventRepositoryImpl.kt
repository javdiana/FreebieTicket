package com.javdiana.freebleticket.view.model.repository

import android.annotation.TargetApi
import android.os.Build
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.utils.DateUtil

class EventRepositoryImpl : EventRepository {
    @TargetApi(Build.VERSION_CODES.O)
    companion object {
        val events: ArrayList<Event> by lazy {
            arrayListOf(
                Event(0, "Event 1", DateUtil.stringToDate("22/11/19"), "Indle rock", 40, 60),
                Event(1, "Event 2", DateUtil.stringToDate("23/11/19"), "Pop music", 60, 80),
                Event(2, "Event 3", DateUtil.stringToDate("24/11/19"), "Rock music", 50, 70)
            )
        }
    }


    override fun getEvents(): ArrayList<Event> {
        return events
    }
}