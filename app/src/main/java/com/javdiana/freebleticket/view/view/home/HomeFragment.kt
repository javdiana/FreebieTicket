package com.javdiana.freebleticket.view.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.extensions.getMonth
import com.javdiana.freebleticket.view.extensions.updateStatusBar
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.view.adapter.CategoryAdapter
import com.javdiana.freebleticket.view.view.adapter.EventsAdapter
import com.javdiana.freebleticket.view.view.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private val detailItem: (Event) -> Unit = {
        startActivity(Intent(activity, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EVENT_ID, it.id)
        })
    }

    private val deleteItem: (Event) -> Unit = {
        homeViewModel.deleteEvent(it)
    }

    private val adapterEvent = EventsAdapter(R.layout.item_event, detailItem, deleteItem)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getListEvents()
        homeViewModel.getListCollections()
        homeViewModel.getListCategories()

        initEvents()
        initCollections()
        initDiscovers()
        initUpcomingEvents()

        buttonSetFilters.setOnClickListener { setFilters() }
    }

    override fun onResume() {
        super.onResume()
        activity?.updateStatusBar(HOME_TAG)
    }

    private fun initEvents() {
        rvEvents.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rvEvents.adapter = adapterEvent
        homeViewModel.events.observe(this, Observer {
            if (it.isEmpty()){
                tvIsEventsEmpty.visibility = View.VISIBLE
            } else {
                tvIsEventsEmpty.visibility = View.GONE
            }
            adapterEvent.submitList(it)
            adapterEvent.notifyDataSetChanged()//find diff solution
        })
    }

    private fun initCollections() {
        rvCollections.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = EventsAdapter(R.layout.item_collection, detailItem, deleteItem)
        rvCollections.adapter = adapter
        homeViewModel.collections.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initDiscovers() {
        rvCategories.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = CategoryAdapter(R.layout.item_category_light_with_image)
        rvCategories.adapter = adapter
        homeViewModel.categories.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initUpcomingEvents() {
        homeViewModel.getListUpcomingEvents()

        homeViewModel.upcomingEvents.observe(this, Observer {
            initViewsUpcomingEvents(it[0], it[1], it.size)
        })
    }

    private fun initViewsUpcomingEvents(event1: Event, event2: Event, size: Int) {
        tvMonth.text = event1.date.getMonth()

        val layoutEvents: LinearLayout = upcomingEventsContainer
        val upcomingEventViewTomorrow = UpcomingEventView(activity)
        val upcomingEventViewDayAfterTomorrow = UpcomingEventView(activity)
        layoutEvents.addView(upcomingEventViewTomorrow)
        layoutEvents.addView(upcomingEventViewDayAfterTomorrow)

        upcomingEventViewTomorrow.createView(event1, deleteItem, size)
        upcomingEventViewDayAfterTomorrow.createView(event2, deleteItem, size)
    }

    private fun setFilters() {
        if (filters.visibility == View.GONE) {
            containerRvEvents.visibility = View.GONE
            filters.visibility = View.VISIBLE
        } else {
            containerRvEvents.visibility = View.VISIBLE
            filters.visibility = View.GONE
        }
    }

    companion object {
        const val HOME_TAG = "HOME_TAG"
    }

}