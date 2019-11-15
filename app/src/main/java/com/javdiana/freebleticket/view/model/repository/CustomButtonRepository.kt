package com.javdiana.freebleticket.view.model.repository

import com.javdiana.freebleticket.view.model.entity.CustomButton

interface CustomButtonRepository {
    fun getDiscoverButtons():ArrayList<CustomButton>
}