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
import com.javdiana.freebleticket.view.model.entity.Category
import com.javdiana.freebleticket.view.view.home.adapter.CategoryAdapter.CategoryHolder
import kotlinx.android.synthetic.main.item_category_light.view.*

class CategoryAdapter(@LayoutRes private val layoutFile:Int) : ListAdapter<Category, RecyclerView.ViewHolder>(CategoryPostDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoryHolder) {
            holder.bind(getItem(position))
        } else if (holder is PopularCategoryHolder) {
            holder.bind(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(layoutFile, parent, false)
        return when(layoutFile){
            R.layout.item_category_light -> CategoryHolder(view)
            R.layout.item_category_dark -> PopularCategoryHolder(view)
            else -> PopularCategoryHolder(view)
        }
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(button: Category) {
            itemView.btnImageCategory.clipToOutline = true
            Glide.with(itemView).load(button.image).into(itemView.btnImageCategory)
            itemView.btnTitleCategory.text = button.title
            itemView.btnTitleCategory.setTextColor(button.color)
        }
    }

    class PopularCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(button: Category){
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