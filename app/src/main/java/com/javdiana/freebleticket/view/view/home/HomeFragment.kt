package com.javdiana.freebleticket.view.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.view.Constants
import com.javdiana.freebleticket.view.view.details.DetailsActivity
import com.javdiana.freebleticket.view.view.home.adapter.CustomButtonAdapter
import com.javdiana.freebleticket.view.view.home.adapter.EventsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

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
        homeViewModel.getListButtons()

        initEvents()
        initCollections()
        initDiscovers()
    }

    private fun initEvents() {
        rvEvents.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = EventsAdapter(R.layout.item_event, detailItem)
        rvEvents.adapter = adapter
        homeViewModel.events.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private val detailItem:(Event)-> Unit = {
        val intent = Intent(this.activity, DetailsActivity::class.java)
        intent.putExtra(Constants.EXTRA_ID, it.id)
        startActivity(intent)
    }

    private fun initCollections() {
        rvCollections.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = EventsAdapter(R.layout.item_collection, detailItem)
        rvCollections.adapter = adapter
        homeViewModel.collections.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initDiscovers() {
        rvDiscover.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = CustomButtonAdapter()
        rvDiscover.adapter = adapter
        homeViewModel.buttons.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}