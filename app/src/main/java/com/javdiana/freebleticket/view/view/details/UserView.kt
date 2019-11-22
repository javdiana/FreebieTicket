package com.javdiana.freebleticket.view.view.details

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserView(context: Context) : LinearLayout(context) {
    init {
        LayoutInflater.from(context)
            .inflate(R.layout.item_user, this, true)
    }

    fun createView(user: User) {
        nextEvent.text = "no"
        typeOfUser.text = user.type
        nameOfUser.text = user.name

    }
}