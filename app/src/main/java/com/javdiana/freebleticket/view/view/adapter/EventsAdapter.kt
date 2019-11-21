package com.javdiana.freebleticket.view.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.extensions.formatToString
import com.javdiana.freebleticket.view.model.entity.Event
import kotlinx.android.synthetic.main.item_collection.view.*
import kotlinx.android.synthetic.main.item_event.view.*


class EventsAdapter(
    @LayoutRes private val  layoutFile: Int,
    private val detailItem: (Event) -> Unit,
    private val deleteItem: (Event) -> Unit
) : ListAdapter<Event, RecyclerView.ViewHolder>(EventsPostDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EventHolder) {
            holder.bind(getItem(position), detailItem, deleteItem)
        } else if (holder is CollectionHolder) {
            holder.bind(getItem(position), detailItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(layoutFile, parent, false)
        return when(layoutFile){
            R.layout.item_event -> EventHolder(view)
            R.layout.item_collection -> CollectionHolder(view)
            else -> CollectionHolder(view)
        }
    }

    class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: Event, detailItem: (Event) -> Unit, deleteItem: (Event) -> Unit) {
//            itemView.imageEvent.clipToOutline = true
            itemView.tvDateEvent.text = event.date.formatToString().toUpperCase()
            itemView.tvEvent.text = event.name
            itemView.tvTypeEvent.text = event.typeMusic
            itemView.tvCostEvent.text = String.format(
                itemView.resources.getString(R.string.show_2_string),
                event.costLow,
                event.costHigh
            )
            itemView.imageDeleteItem.setOnClickListener { deleteItem(event) }
            itemView.setOnClickListener { detailItem(event) }
        }
    }

    class CollectionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: Event, detailItem: (Event) -> Unit) {
//            itemView.imageCollection.clipToOutline = true
            itemView.titleCollection.text = event.name
            itemView.typeCollection.text = event.typeMusic
            itemView.learnMoreCollection.setOnClickListener { detailItem(event) }
            itemView.setOnClickListener { detailItem(event) }
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