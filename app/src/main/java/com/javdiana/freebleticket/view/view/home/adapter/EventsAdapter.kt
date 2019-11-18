package com.javdiana.freebleticket.view.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javdiana.freebleticket.R.id.collectionItem
import com.javdiana.freebleticket.R.id.eventItem
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.utils.DateUtil
import com.javdiana.freebleticket.view.view.home.adapter.EventsAdapter.EventHolder
import kotlinx.android.synthetic.main.item_collection.view.*
import kotlinx.android.synthetic.main.item_event.view.*


class EventsAdapter(@LayoutRes private val layout: Int, private val openClick: (Event) -> Unit) :
    ListAdapter<Event, EventHolder>(EventsPostDiffCallback()) {

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(getItem(position), openClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return EventHolder(view)
    }

    class EventHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event, openClick: (Event) -> Unit) {
            when (itemView.id) {
                eventItem -> {
                    itemView.imageEvent.clipToOutline = true
                    itemView.textviewDate.text = DateUtil.dateToString(event.date)
                    itemView.textviewFestival.text = event.name
                    itemView.textviewType.text = event.type
                    itemView.textviewCost.text = "€${event.costLow}-${event.costHigh}"
                }
                collectionItem -> {
                    itemView.imageCollection.clipToOutline = true
                    itemView.titleCollection.text = event.name
                    itemView.typeCollection.text = event.type
                    itemView.learnMoreCollection.setOnClickListener { openClick(event) }
                }
            }

            itemView.setOnClickListener{openClick(event)}
        }
    }
}

class EventsPostDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

} 