package com.javdiana.freebleticket.view.model.repository

import android.annotation.TargetApi
import android.os.Build
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.entity.Location
import com.javdiana.freebleticket.view.model.entity.Role
import com.javdiana.freebleticket.view.model.entity.Performer
import com.javdiana.freebleticket.view.utils.DateUtil

class EventRepositoryImpl : EventRepository {
    override fun getEvent(id: Long) : Event{
        lateinit var event: Event
        events.forEach { e ->
            if(e.id == id){
                event = e
                return@forEach
            }
        }
        return event
    }

    @TargetApi(Build.VERSION_CODES.O)
    companion object {
        const val details = "Free directories: directories are perfect for\n" +
                "customers that are searching for a particular\n" +
                "topic. What’s great about them is that you only\n" +
                "have to post once and they are good for long\n" +
                "periods of time. It saves a lot of your time when\n" +
                "you don’t have to resubmit your information\n" +
                "every week…"
        const val updates = "July 24, 2019\n" +
                "\n" +
                "Customers that are searching for a particular\n" +
                "topic. What’s great about them is that you only\n" +
                "have…"

        private val performers = listOf(
            Performer(0,"Performer1", "", "Indle rock", DateUtil.stringToDate("23/11/19"), Role.PERFORMERS),
            Performer(1, "Performer2", "", "Rock music", DateUtil.stringToDate("24/11/19"), Role.PERFORMERS)
        )

        private val organizers =
            listOf(Performer(0, "Organizer1", "", "Indle rock", DateUtil.stringToDate("23/11/19"), Role.ORGANIZATORS))

        val events: ArrayList<Event> by lazy {
            arrayListOf(
                Event(
                    0, "Event 1", DateUtil.stringToDate("22/11/19"), "Indle rock",
                    40, 60, "", "Flora club", details, updates, "",
                    Location(0, 0), performers, organizers, "radio NV"
                ),
                Event(
                    1, "Event 2", DateUtil.stringToDate("23/11/19"), "Pop music",
                    60, 80, "", "Bartka club", details, updates, "",
                    Location(1, 1), performers, organizers, "radio NV"
                ),
                Event(
                    2, "Event 3", DateUtil.stringToDate("24/11/19"), "Rock music",
                    50, 70, "", "Rosha club", details, updates, "",
                    Location(2, 2), performers, organizers, "radio NV"
                )
            )
        }
    }


    override fun getEvents(): ArrayList<Event> {
        return events
    }
}