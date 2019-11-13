package com.javdiana.freebleticket.view.model.entity

data class Event(
    val id: Long,
    val name: String,
    val date: Long,
    val type: String,
    val costLow: Int,
    val costHigh: Int
)