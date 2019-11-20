package com.javdiana.freebleticket.view.view.tickets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javdiana.freebleticket.view.model.entity.Category
import com.javdiana.freebleticket.view.model.repository.CategoryRepository

class TicketsViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {
    val categories = MutableLiveData<ArrayList<Category>>()

    fun getListCategories() {
        categories.postValue(categoryRepository.getPopularCategories())
    }
}