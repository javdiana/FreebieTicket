package com.javdiana.freebleticket.view.view.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.Performer
import com.javdiana.freebleticket.view.utils.DateUtil
import com.javdiana.freebleticket.view.view.details.adapter.UserAdapter.OrganizerHolder
import kotlinx.android.synthetic.main.item_performer.view.*

class UserAdapter : ListAdapter<Performer, OrganizerHolder>(OrganizerPostDiffCallback()) {

    override fun onBindViewHolder(holder: OrganizerHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizerHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_performer, parent, false)
        return OrganizerHolder(view)
    }

    class OrganizerHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(event: Performer) {
            itemView.imageOrganizer.clipToOutline = true
            itemView.nameOfOrganizator.text = event.name
            itemView.typeOrgsnizator.text = event.type
            itemView.dateOrganizator.text = DateUtil.dateToString(event.date)
        }
    }
}

class OrganizerPostDiffCallback : DiffUtil.ItemCallback<Performer>() {
    override fun areItemsTheSame(oldItem: Performer, newItem: Performer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Performer, newItem: Performer): Boolean {
        return oldItem == newItem
    }

}