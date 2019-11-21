package com.javdiana.freebleticket.view.view.search

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.R.layout.fragment_search
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.entity.TypeCategory
import com.javdiana.freebleticket.view.view.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment(), OnMapReadyCallback {
    private var map: GoogleMap? = null
    private val searchViewModel: SearchViewModel by viewModel()

    private val showMarkerPosition: (Event) ->  Unit  = {
        setMarkerEvent(it)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(it.location, 16f))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchViewModel.getEvents()
        searchViewModel.getCategories()

        return inflater.inflate(fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategories()

        val mapFragment =childFragmentManager.findFragmentById(R.id.mapCategory) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initViews()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap

        map?.let {
            it.uiSettings.isCompassEnabled = true
            it.isIndoorEnabled = true
            it.setMapStyle(MapStyleOptions.loadRawResourceStyle(activity, R.raw.map_style))
        }

        searchViewModel.events.observe(this, Observer {
            searchViewModel.events.value?.let {
                it.filter { event -> event.typeCategory == TypeCategory.MUSIC }.map { e ->
                    setMarkerEvent(e)
                }

                it.filter { event -> event.typeCategory == TypeCategory.SPORT }.map { e ->
                    setMarkerEvent(e)
                }

                map?.moveCamera(CameraUpdateFactory.newLatLngZoom(it.last().location, 16f))
            }
        })
    }

    private fun initCategories() {
        rvCategoriesSearch.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = CategoryAdapter(R.layout.item_category_light, showMarkerPosition)
        rvCategoriesSearch.adapter = adapter
        searchViewModel.categories.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun setMarkerEvent(event: Event){
        map?.addMarker(
            MarkerOptions().position(event.location).title(event.name)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.music_marker))
        )
    }

    private fun initViews() {
        imageMyLocation.setOnClickListener {
            if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {

                map?.let {
                    it.isMyLocationEnabled = true
                    it.uiSettings.isMyLocationButtonEnabled = true

                    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity!!)
                    val location = fusedLocationProviderClient.lastLocation
                    location.addOnCompleteListener{ task ->
                        val currentLocation = task.result as Location
                        moveCamera(currentLocation)
                    }
                }

                } else {
                setPermissionsLocation()
            }
        }
    }

    private fun moveCamera(coordinates: Location) {
        val location = LatLng(coordinates.latitude, coordinates.longitude)
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))
    }

    private fun setPermissionsLocation() {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(activity!!, permissions, 0)
    }
}