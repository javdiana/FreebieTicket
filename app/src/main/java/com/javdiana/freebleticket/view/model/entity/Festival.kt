package com.javdiana.freebleticket.view.model.entity

data class Festival(
    val id: Long,
    val name: String,
    val date: Long?,//add parsers
    val place: String,
    val costLow: Int,
    val costHigh: Int
)