package com.javdiana.freebleticket.view.model.repository

import com.javdiana.freebleticket.view.model.entity.Festival

interface FestivalRepository {
    fun getFestivals(): List<Festival>;
}