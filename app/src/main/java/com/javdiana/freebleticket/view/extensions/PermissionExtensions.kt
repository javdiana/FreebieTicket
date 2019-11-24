package com.javdiana.freebleticket.view.extensions

import android.Manifest
import android.app.Activity
import androidx.core.app.ActivityCompat

fun Activity.setLocationPermissions() {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    ActivityCompat.requestPermissions(this, permissions, 0)
}