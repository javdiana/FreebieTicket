package com.javdiana.freebleticket.view.model.repository

import com.javdiana.freebleticket.view.extensions.formatToLong
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.entity.Location
import com.javdiana.freebleticket.view.model.entity.Performer
import com.javdiana.freebleticket.view.model.entity.Role

class EventRepositoryImpl : EventRepository {

    private lateinit var events: ArrayList<Event>
    private lateinit var upcomingEvents: ArrayList<Event>

    override fun getEvent(id: Long): Event {
        return events.first { it.id == id }
    }

    override fun deleteEvent(event: Event) {
        events.remove(event)
    }

    override fun getEvents(): ArrayList<Event> {
        val details = "Free directories: directories are perfect for\n" +
                "customers that are searching for a particular\n" +
                "topic. What’s great about them is that you only\n" +
                "have to post once and they are good for long\n" +
                "periods of time. It saves a lot of your time when\n" +
                "you don’t have to resubmit your information\n" +
                "every week…"
        val updates = "July 24, 2019\n" +
                "\n" +
                "Customers that are searching for a particular\n" +
                "topic. What’s great about them is that you only\n" +
                "have…"

        val performers = listOf(
            Performer(
                0,
                "Performer1",
                "",
                "Indle rock",
                ("TUESDAY AUG 9PM").formatToLong(),
                Role.PERFORMER
            ),
            Performer(
                1,
                "Performer2",
                "",
                "Rock music",
                ("WEDNESDAY SEP 7PM").formatToLong(),
                Role.PERFORMER
            )
        )

        val organizers =
            listOf(
                Performer(
                    0,
                    "Organizer1",
                    "",
                    "Indle rock",
                    ("SUNDAY NOV 3PM").formatToLong(),
                    Role.ORGANIZER
                )
            )


        events = arrayListOf(
            Event(
                0, "Event 1", ("SUNDAY NOVEMBER 6, 3PM").formatToLong(), "Indle rock",
                40, 60, "", "Flora club", details, updates, "",
                Location(0, 0), performers, organizers, "radio NV"
            ),
            Event(
                1, "Event 2", ("WEDNESDAY SEPTEMBER 11, 7PM").formatToLong(), "Pop music",
                60, 80, "", "Bartka club", details, updates, "",
                Location(1, 1), performers, organizers, "radio NV"
            ),
            Event(
                2, "Event 3", ("FRIDAY SEPTEMBER 11, 7PM").formatToLong(), "Rock music",
                50, 70, "", "Rosha club", details, updates, "",
                Location(2, 2), performers, organizers, "radio NV"
            )
        )
        return events
    }

    override fun getUpcomingEvents(): ArrayList<Event> {
        upcomingEvents = arrayListOf(
            Event(
                3, "Event 4", ("TUESDAY NOVEMBER 20, 9PM").formatToLong(), "Rock music",
                50, 70, "", "Goyra club", "", "", "",
                Location(2, 2), listOf(), listOf(), "radio NV"
            ),
            Event(
                4, "Event 5", ("TUESDAY NOVEMBER 21, 9PM").formatToLong(), "Rock music",
                50, 70, "", "Dolche club", "", "", "",
                Location(2, 2), listOf(), listOf(), "radio NV"
            )
        )
        return upcomingEvents
    }
}