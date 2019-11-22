package com.javdiana.freebleticket.view.model.repository

import com.google.android.gms.maps.model.LatLng
import com.javdiana.freebleticket.view.extensions.formatToLong
import com.javdiana.freebleticket.view.model.entity.*

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
        val updates = "Customers that are searching for a particular\n" +
                "topic. What’s great about them is that you only\n" +
                "have…"

        val performers = arrayListOf(
            User(0, "Performer1", "", "Indle rock", "21-06-2019 3AM".formatToLong(), Role.PERFORMER),
            User(1, "Performer2", "", "Rock music", "21-07-2019 3AM".formatToLong(), Role.PERFORMER)
        )

        val organizers = arrayListOf(
            User(0, "Organizer1", "", "Indle rock", "21-08-2019 3AM".formatToLong(), Role.ORGANIZER ))


        events = arrayListOf(
            Event(
                0, "Event 1", "21-05-2019 5AM".formatToLong(), "Indle rock",
                40, 60, "", "Flora club", details, updates, "",
                LatLng(0.055551, 0.055551), performers, organizers, "radio NV", TypeCategory.MUSIC,
                "Shevchennka 22", "Ukraine"
            ),
            Event(
                1, "Event 2", "24-04-2019 4AM".formatToLong(), "Pop music",
                60, 80, "", "Bartka club", details, updates, "",
                LatLng(25.932637, 10.055551), performers, organizers, "radio NV", TypeCategory.SPORT, "" +
                        "Golovna 11", "USA"
            ),
            Event(
                2, "Event 3", "21-03-2019 3AM".formatToLong(), "Rock music",
                50, 70, "", "Rosha club", details, updates, "",
                LatLng(20.055551, 25.932637), performers, organizers, "radio NV", TypeCategory.MUSIC,
                "Golovna 11", "Italy"
            ),
            Event(
                3, "Event 4", "22-02-2019 1AM".formatToLong(), "Rock music",
                50, 70, "", "Goyra club", details, updates, "",
                LatLng(48.290900, 30.055551), arrayListOf(), arrayListOf(), "radio NV", TypeCategory.SPORT,
                "Golovna 11", "Germany"
            ),
            Event(
                4, "Event 5", "23-01-2019 2AM".formatToLong(), "Rock music",
                50, 70, "", "Dolche club", details, updates, "",
                LatLng(48.290988, 25.932637), arrayListOf(), arrayListOf(), "radio NV", TypeCategory.MUSIC,
                "Golovna 11", "France"
            )
        )
        return events
    }

    override fun getUpcomingEvents(): ArrayList<Event> {
        upcomingEvents = arrayListOf(
            Event(
                3, "Event 4", "22-11-2019 3AM".formatToLong(), "Rock music",
                50, 70, "", "Goyra club", "", "", "",
                LatLng(30.0, 30.0), arrayListOf(), arrayListOf(), "radio NV", TypeCategory.SPORT, "", ""
            ),
            Event(
                4, "Event 5", "23-11-2019 3AM".formatToLong(), "Rock music",
                50, 70, "", "Dolche club", "", "", "",
                LatLng(40.0, 40.0), arrayListOf(), arrayListOf(), "radio NV", TypeCategory.MUSIC, "", ""
            )
        )
        return upcomingEvents
    }
}