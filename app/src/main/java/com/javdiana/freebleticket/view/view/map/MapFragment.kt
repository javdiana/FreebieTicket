package com.javdiana.freebleticket.view.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.R.layout.fragment_map
import com.javdiana.freebleticket.view.extensions.setLocationPermissions
import com.javdiana.freebleticket.view.extensions.updateStatusBar
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.view.ConfigMap
import com.javdiana.freebleticket.view.view.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.android.viewmodel.ext.android.viewModel


class MapFragment : Fragment() {
    private var map: GoogleMap? = null
    private val mapViewModel: MapViewModel by viewModel()
    private lateinit var configMap: ConfigMap

    private val setPermissionsLocation: () -> Unit = {
        activity!!.setLocationPermissions()
    }

    private val showMarkerPosition: (Event) -> Unit = {
        configMap.setMarkerEvent(it)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(it.location, 16f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapViewModel.getEvents()
        mapViewModel.getCategories()

        return inflater.inflate(fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategories()

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapCategory) as SupportMapFragment
        mapViewModel.events.observe(this, Observer {
            configMap = ConfigMap(context!!, mapFragment, it)
        })

        initViews()
    }

    override fun onResume() {
        super.onResume()
        activity?.updateStatusBar(MAP_TAG)
    }

    private fun initCategories() {
        rvCategoriesSearch.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = CategoryAdapter(R.layout.item_category_light, showMarkerPosition)
        rvCategoriesSearch.adapter = adapter
        mapViewModel.categories.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initViews() {
        imageMyLocation.setOnClickListener {
            configMap.setMyLocation(setPermissionsLocation)
        }
    }

    companion object {
        const val MAP_TAG = "MAP_TAG"
    }

}