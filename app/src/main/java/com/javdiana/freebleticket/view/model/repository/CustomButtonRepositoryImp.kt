package com.javdiana.freebleticket.view.model.repository

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.CustomButton

class CustomButtonRepositoryImp : CustomButtonRepository {

    @TargetApi(Build.VERSION_CODES.O) companion object {

        val BUTTONS_DISCOVER: ArrayList<CustomButton> by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                arrayListOf(
                    CustomButton(0,"YOUR AREA", R.drawable.ic_location, Color.parseColor("#FC1055")),
                    CustomButton(1,"MUSIC", R.drawable.ic_note_blue, Color.parseColor("#5798FF")),
                    CustomButton(2,"SPORTS", R.drawable.ic_sports, Color.parseColor("#E69960"))
                )
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        }
    }

    override fun getDiscoverButtons(): ArrayList<CustomButton> {
        return BUTTONS_DISCOVER
    }

}