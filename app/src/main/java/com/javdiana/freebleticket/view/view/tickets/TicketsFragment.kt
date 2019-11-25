package com.javdiana.freebleticket.view.view.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.extensions.updateStatusBar
import com.javdiana.freebleticket.view.view.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_tickets.*
import org.koin.android.viewmodel.ext.android.viewModel

class TicketsFragment : Fragment() {
    private val ticketsViewModel: TicketsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ticketsViewModel.getListCategories()

        initCategories()
        activity?.updateStatusBar("TAG_ACTIVITY_DETAIL")
    }

    override fun onResume() {
        super.onResume()
        activity?.updateStatusBar("TAG_ACTIVITY_DETAIL")
    }

    private fun initCategories() {
        rvCategoriesPopular.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = CategoryAdapter(R.layout.item_category_dark)
        rvCategoriesPopular.adapter = adapter
        ticketsViewModel.categories.observe(this, Observer {
            adapter.submitList(it)
        })
    }

}