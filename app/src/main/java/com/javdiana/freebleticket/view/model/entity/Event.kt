package com.javdiana.freebleticket.view.model.entity

data class Event(
    val id: Long,
    val name: String,
    val date: Long,
    val type: String,
    val costLow: Int,
    val costHigh: Int,
    val imageEvent: String,
    val place: String,
    val details: String,
    val updates: String,
    val imagePoster: String,
    val location: Location,
    val performers: List<Performer>,
    val organizers: List<Performer>,
    val source:String
)