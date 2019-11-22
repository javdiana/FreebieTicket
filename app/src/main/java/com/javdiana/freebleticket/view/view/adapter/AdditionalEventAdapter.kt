package com.javdiana.freebleticket.view.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.view.adapter.AdditionalEventAdapter.AdditionalEventHolder
import kotlinx.android.synthetic.main.item_event_less_data.view.*

class AdditionalEventAdapter(
) : ListAdapter<Event, AdditionalEventHolder>(AdditionalEventDiffCallback()) {

    override fun onBindViewHolder(holder: AdditionalEventHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalEventHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_less_data, parent, false)
        return AdditionalEventHolder(view)
    }

    class AdditionalEventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: Event) {
//            itemView.imageEvent.clipToOutline = true
            itemView.tvEventLess.text = event.name
            itemView.tvTypeEventLess.text = event.typeMusic
            itemView.tvCostEventLess.text = String.format(
                itemView.resources.getString(R.string.show_2_string),
                event.costLow,
                event.costHigh
            )
        }
    }
}

class AdditionalEventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

}