package com.javdiana.freebleticket.view.view.tickets

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.javdiana.freebleticket.R
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
        initStatusBar()

        return inflater.inflate(R.layout.fragment_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ticketsViewModel.getListCategories()

        initCategories()
    }

    private fun initStatusBar() {
        activity!!.let {
            it.window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                statusBarColor = Color.TRANSPARENT
            }
        }
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