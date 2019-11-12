package com.javdiana.freebleticket.view.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.Festival
import kotlinx.android.synthetic.main.festival_item.view.*

class FestivalsAdapter :
    ListAdapter<Festival, FestivalsAdapter.ListFestivalHolder>(PostDiffCallback()) {
    private lateinit var context: Context

    override fun onBindViewHolder(holder: ListFestivalHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFestivalHolder {
        context = parent.context
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.festival_item, parent, false)
        return ListFestivalHolder(view)
    }

    class ListFestivalHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(festival: Festival) {
//            itemView.background = itemView.resources.getDrawable(R.drawable.item_recyclerview) add change photo
//            itemView.textviewDate.text = festival.date.toString()
            itemView.textviewFestival.text = festival.name
            itemView.textviewPlace.text = festival.place
            itemView.textviewCost.text = "€${festival.costLow}-${festival.costHigh}"
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Festival>() {
    override fun areItemsTheSame(oldItem: Festival, newItem: Festival): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Festival, newItem: Festival): Boolean {
        return oldItem == newItem
    }

} 