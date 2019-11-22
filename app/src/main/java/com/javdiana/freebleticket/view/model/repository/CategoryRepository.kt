package com.javdiana.freebleticket.view.model.repository

import com.javdiana.freebleticket.view.model.entity.Category

interface CategoryRepository {

    fun getCategories() : ArrayList<Category>

    fun getPopularCategories() : ArrayList<Category>

}