package com.javdiana.freebleticket.view.view.home

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.extensions.getDayOfMonth
import com.javdiana.freebleticket.view.extensions.getDayOfWeek
import com.javdiana.freebleticket.view.model.entity.Event
import kotlinx.android.synthetic.main.item_upcoming_event.view.*

class UpcomingEventView(context: Context?) : LinearLayout(context) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.item_upcoming_event, this, true)
    }

    fun createView(event: Event, deleteItem: (Event) -> Unit, sizeItems: Int) {
        imageEventUpcoming.clipToOutline = true
        imageMoreUpcoming.clipToOutline = true

        imageDeleteItemUpcoming.setOnClickListener { deleteItem(event) }
        tvEventUpcoming.text = event.name
        tvTypeEventUpcoming.text = event.type
        tvCostEventUpcoming.text = String.format(
            context.resources.getString(R.string.show_2_string),
            event.costLow,
            event.costHigh
        )
        when (val items = sizeItems - 1) {
            1 -> tvNumberOfEventUpcoming.text =
                String.format("%s - %s", items.toString(), resources.getString(R.string.event))
            else -> tvNumberOfEventUpcoming.text =
                String.format("%s - %s", items.toString(), resources.getString(R.string.events))
        }

        tvDayOfMonth.text = event.date.getDayOfMonth()
        tvDayOfWeek.text = event.date.getDayOfWeek()
    }
}