package com.javdiana.freebleticket.view.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.Category
import com.javdiana.freebleticket.view.view.home.adapter.CustomButtonAdapter.CategoryHolder
import kotlinx.android.synthetic.main.item_category.view.*

class CustomButtonAdapter :
    ListAdapter<Category, CategoryHolder>(CategoryPostDiffCallback()) {

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryHolder(view)
    }

    class CategoryHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(button: Category) {
            itemView.btnImageCategory.clipToOutline = true
            Glide.with(itemView).load(button.image).into(itemView.btnImageCategory)
            itemView.btnTitleCategory.text = button.title
            itemView.btnTitleCategory.setTextColor(button.color)
        }
    }
}

class CategoryPostDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}