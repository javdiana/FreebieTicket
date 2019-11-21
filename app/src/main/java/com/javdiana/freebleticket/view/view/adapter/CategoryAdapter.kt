package com.javdiana.freebleticket.view.view.adapter

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
import com.javdiana.freebleticket.view.model.entity.Event
import kotlinx.android.synthetic.main.item_category_light_with_image.view.*

class CategoryAdapter(
    @LayoutRes private val layoutFile: Int) :
    ListAdapter<Category, RecyclerView.ViewHolder>(CategoryPostDiffCallback()) {
    private lateinit var showMarkerPosition: (Event) ->  Unit

    constructor(@LayoutRes layoutFile: Int, showMarkerPosition: (Event) ->  Unit) : this(layoutFile) {
        this.showMarkerPosition = showMarkerPosition
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryHolder -> holder.bind(getItem(position))
            is PopularCategoryHolder -> holder.bind(getItem(position))
            is SearchCategoryHolder -> holder.bind(getItem(position), showMarkerPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(layoutFile, parent, false)
        return when (layoutFile) {
            R.layout.item_category_light_with_image -> CategoryHolder(view)
            R.layout.item_category_dark -> PopularCategoryHolder(view)
            R.layout.item_category_light -> SearchCategoryHolder(view)
            else -> SearchCategoryHolder(view)
        }
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category) {
//            itemView.btnImageCategory.clipToOutline = true
            Glide.with(itemView).load(category.image).into(itemView.btnImageCategory)
            itemView.btnTitleCategory.text = category.title
            itemView.btnTitleCategory.setTextColor(category.color)
        }
    }

    class PopularCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            itemView.btnTitleCategory.text = category.title
            itemView.btnTitleCategory.setTextColor(category.color)
        }
    }

    class SearchCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category, showMarkerPosition: (Event) ->  Unit) {
            itemView.btnTitleCategory.text = category.title
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