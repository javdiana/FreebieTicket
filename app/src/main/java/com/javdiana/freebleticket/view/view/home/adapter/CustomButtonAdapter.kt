package com.javdiana.freebleticket.view.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.R.id
import com.javdiana.freebleticket.R.id.*
import com.javdiana.freebleticket.view.model.entity.CustomButton
import com.javdiana.freebleticket.view.view.home.adapter.CustomButtonAdapter.CustomButtonHolder
import kotlinx.android.synthetic.main.item_button.view.*

class CustomButtonAdapter :
    ListAdapter<CustomButton, CustomButtonHolder>(ButtonPostDiffCallback()) {

    override fun onBindViewHolder(holder: CustomButtonHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomButtonHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
        return CustomButtonHolder(view)
    }

    class CustomButtonHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(button: CustomButton) {
            itemView.btnImage.clipToOutline = true
            Glide.with(itemView).load(button.image).into(itemView.btnImage)
            itemView.btnName.text = button.title
            itemView.btnName.setTextColor(button.color)
        }

    }
}

class ButtonPostDiffCallback : DiffUtil.ItemCallback<CustomButton>() {
    override fun areItemsTheSame(oldItem: CustomButton, newItem: CustomButton): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CustomButton, newItem: CustomButton): Boolean {
        return oldItem == newItem
    }

}