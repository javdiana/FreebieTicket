package com.javdiana.freebleticket.view.view.map

import android.Manifest
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.R.layout.fragment_search
import com.javdiana.freebleticket.view.extensions.toast
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.view.ConfigMap
import com.javdiana.freebleticket.view.view.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel


class MapFragment : Fragment() {
    private var map: GoogleMap? = null
    private val mapViewModel: MapViewModel by viewModel()
    private lateinit var configMap: ConfigMap

    private val showMessage: () -> Unit = {
        activity?.toast(getString(R.string.internet_gps_validation))
    }

    private val showMarkerPosition: (Event) ->  Unit  = {
        configMap.setMarkerEvent(it)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(it.location, 16f))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mapViewModel.getEvents()
        mapViewModel.getCategories()

        return inflater.inflate(fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategories()

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapCategory) as SupportMapFragment
        mapViewModel.events.observe(this, Observer {
            configMap = ConfigMap(context!!, mapFragment, it, showMessage)
        })

        initViews()
    }

    override fun onResume() {
        super.onResume()
        initStatusBar()
    }

    private fun initStatusBar() {
        activity?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.window.apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        statusBarColor = Color.TRANSPARENT
                        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    }
                }
            }
        }
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
    private val setPermissionsLocation: () -> Unit = {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(activity!!, permissions, 0)
    }

    private fun initViews() {
        imageMyLocation.setOnClickListener {
            configMap.setMyLocation(setPermissionsLocation)
        }
    }

}