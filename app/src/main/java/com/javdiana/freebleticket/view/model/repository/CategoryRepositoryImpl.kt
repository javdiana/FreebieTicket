package com.javdiana.freebleticket.view.model.repository

import android.graphics.Color
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.Category

class CategoryRepositoryImpl : CategoryRepository {

    override fun getCategories(): ArrayList<Category> {
        return arrayListOf(
            Category(0, "YOUR AREA", R.drawable.ic_location, Color.parseColor("#FC1055")),
            Category(1, "MUSIC", R.drawable.ic_note_blue, Color.parseColor("#5798FF")),
            Category(2, "SPORTS", R.drawable.ic_sports, Color.parseColor("#E69960"))
        )
    }

}