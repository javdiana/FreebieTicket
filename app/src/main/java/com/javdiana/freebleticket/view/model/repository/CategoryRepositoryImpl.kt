package com.javdiana.freebleticket.view.model.repository

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.Category
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CategoryRepositoryImpl : CategoryRepository {
    override fun getCategories(): ArrayList<Category> {
        return arrayListOf(
            Category(0, "YOUR AREA", R.drawable.ic_location, Color.parseColor("#FC1055")),
            Category(1, "MUSIC", R.drawable.ic_note_blue, Color.parseColor("#5798FF")),
            Category(2, "SPORTS", R.drawable.ic_sports, Color.parseColor("#E69960"))
        )
    }

    override fun getPopularCategories(): ArrayList<Category> {
        return arrayListOf(
            Category(0, "Phil Collins", 0, Color.parseColor("#ffffff")),
            Category(1, "TV on the radio", 0, Color.parseColor("#ffffff")),
            Category(2, "PC Barcelona", 0, Color.parseColor("#ffffff")),
            Category(3, "Linkin Park", 0, Color.parseColor("#ffffff")),
            Category(4, "Morandi", 0, Color.parseColor("#ffffff")),
            Category(5, "Imagine Dragons", 0, Color.parseColor("#ffffff"))
        )
    }
}