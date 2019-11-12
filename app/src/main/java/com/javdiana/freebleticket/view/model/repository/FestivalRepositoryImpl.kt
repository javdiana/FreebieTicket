package com.javdiana.freebleticket.view.model.repository

import android.annotation.TargetApi
import android.content.res.Resources
import android.os.Build
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.Festival

class FestivalRepositoryImpl : FestivalRepository {
    @TargetApi(Build.VERSION_CODES.O)
    companion object {
        val list: List<Festival> by lazy {
            arrayListOf(
                Festival(0, "Festival 1", null, "Plasa hotel", 40, 60),
                Festival(1, "Festival 2", null, "Omega hotel", 60, 80),
                Festival(2, "Festival 3", null, "Tourist hotel", 50, 70)
            )
        }
    }


    override fun getFestivals(): List<Festival> {
        return list
    }
}