package com.javdiana.freebleticket.view.model.entity

data class User(
    val id: Long,
    val name: String,
    val photo: String,
    val type: String,
    val date: Long,
    val role: Role
)