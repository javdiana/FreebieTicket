package com.javdiana.freebleticket.view.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.javdiana.freebleticket.R.drawable
import com.javdiana.freebleticket.R.layout
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.view.home.adapter.EventsAdapter.EventHolder
import kotlinx.android.synthetic.main.event_item.view.*


class EventsAdapter :
    ListAdapter<Event, EventHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(layout.event_item, parent, false)
        return EventHolder(view)
    }

    class EventHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {
            itemView.imageViewBackground.clipToOutline = true

            itemView.textviewFestival.text = event.name
            itemView.textviewType.text = event.type
            itemView.textviewCost.text = "€${event.costLow}-${event.costHigh}"
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

} 