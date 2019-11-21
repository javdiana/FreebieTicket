package com.javdiana.freebleticket.view.model.entity

import com.google.android.gms.maps.model.LatLng

data class Event(
    val id: Long,
    val name: String,
    val date: Long,
    val typeMusic: String,
    val costLow: Int,
    val costHigh: Int,
    val imageEvent: String,
    val place: String,
    val details: String,
    val updates: String,
    val imagePoster: String,
    val location: LatLng,
    val performers: List<Performer>,
    val organizers: List<Performer>,
    val source: String,
    val typeCategory: TypeCategory
)