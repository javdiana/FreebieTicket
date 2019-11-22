package com.javdiana.freebleticket.view.view

import  android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
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
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.entity.TypeCategory

class ConfigMap(
    private val context: Context,
    mapFragment: SupportMapFragment,
    private val events: ArrayList<Event>,
    private val showMessage: () -> Unit
): OnMapReadyCallback {
    private var map: GoogleMap? = null

    init {
        mapFragment.getMapAsync(this)
    }

     override fun onMapReady(googleMap: GoogleMap?) {
         map = googleMap

         val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

         val isGpsActive = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
         val hasNetworks = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

         if (isGpsActive && hasNetworks) {
             map?.let {
                 it.uiSettings.isCompassEnabled = true
                 it.isIndoorEnabled = true
                 it.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style))
             }

             events.let {
                 it.filter { event -> event.typeCategory == TypeCategory.MUSIC }.map { e ->
                     setMarkerEvent(e)
                 }

                 it.filter { event -> event.typeCategory == TypeCategory.SPORT }.map { e ->
                     setMarkerEvent(e)
                 }

                 map?.moveCamera(CameraUpdateFactory.newLatLngZoom(it.last().location, 16f))
             }
         } else{
             showMessage()
         }
     }

    fun setMarkerEvent(event: Event){
        map?.addMarker(
            MarkerOptions().position(event.location).title(event.name)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.music_marker))
        )
    }

    fun setMyLocation(setPermissionsLocation: () -> Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {

            map?.let {
                it.isMyLocationEnabled = true
                it.uiSettings.isMyLocationButtonEnabled = true

                val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
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
    private fun moveCamera(coordinates: Location) {
        val location = LatLng(coordinates.latitude, coordinates.longitude)
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))
    }
}