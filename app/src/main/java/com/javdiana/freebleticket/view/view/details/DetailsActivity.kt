package com.javdiana.freebleticket.view.view.details

import android.Manifest
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.extensions.formatToLongForDetails
import com.javdiana.freebleticket.view.extensions.getMonthDayYear
import com.javdiana.freebleticket.view.extensions.toast
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.model.entity.Role
import com.javdiana.freebleticket.view.model.entity.User
import com.javdiana.freebleticket.view.view.ConfigMap
import com.javdiana.freebleticket.view.view.adapter.AdditionalEventAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.view_more_details_sheet.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailsActivity : AppCompatActivity() {
    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val toolbar = findViewById<Toolbar>(R.id.tbShowDetails)
        setSupportActionBar(toolbar)

        initStatusBar()
        initEvent()

        detailsViewModel.getAdditionalEvents()
    }

    override fun onResume() {
        super.onResume()
        initStatusBar()
    }

    private fun initStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                statusBarColor = Color.TRANSPARENT
            }
        }
    }

    private fun initEvent() {
        val id = intent.getLongExtra(EVENT_ID, 0L)
        detailsViewModel.getEvent(id)

        detailsViewModel.event.observe(this, Observer {
            initViews(it)
        })
    }

    private val showMessage: () -> Unit = {
        toast(getString(R.string.internet_gps_validation))
    }

    private fun initViews(event: Event) {
        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbar.title = event.name

        detailsActivity.costResultDetail.text = String.format(
            resources.getString(R.string.show_2_string),
            event.costLow,
            event.costHigh
        )
        butTicketsDetails.setOnClickListener {
            this.toast(resources.getString(R.string.tickets_bought_message))
            finish()
        }

        titleDetails.text = event.name
        tvDateDetails.text = (event.date).formatToLongForDetails()
        tvAddressCountry.text = String.format("%s, %s", event.address, event.country)
        tvLocationDetails.text = event.place
        tvTypeDetails.text = event.typeMusic
        tvCostDetails.text = String.format(
            resources.getString(R.string.show_2_string),
            event.costLow,
            event.costHigh
        )
        tvPlaceDetails.text = event.place
        sourceDetail.text = event.source

        detailsDetails.setText(String.format(event.details + "\n\n"))
        updatesDetails.setText(String.format(event.updates + "\n\n"))

        tvDateUpdates.text = event.date.getMonthDayYear()
        tvPlaceDetailsMain.text = event.place
        tvAddressCountryDetails.text = String.format("%s, %s", event.address, event.country)

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapLocationDetails) as SupportMapFragment
        val map = ConfigMap(this, mapFragment, arrayListOf(event), showMessage)
        imageMyPositionDetails.setOnClickListener { map.setMyLocation(setPermissionsLocation) }

        initUsers(event.performers)
        initUsers(event.organizers)

        initAdditionalEvents()

        imageBackDetails.setOnClickListener { finish() }
        imageLikeDetails.setOnClickListener { }
        imageUploadDetails.setOnClickListener { }
    }

    private val setPermissionsLocation: () -> Unit = {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    private fun initUsers(users: ArrayList<User>) {
        var layout: LinearLayout

        users.map {
            layout =
                if (it.role == Role.PERFORMER) layoutPerformers
                else layoutOrganizers

            val performerView = UserView(this)
            layout.addView(performerView)
            performerView.createView(it)
        }
    }

    private fun initAdditionalEvents() {
        rvAdditionalEvents.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapterAdditionalEvents = AdditionalEventAdapter()
        rvAdditionalEvents.adapter = adapterAdditionalEvents

        rvMoreEvents.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapterMoreEvents = AdditionalEventAdapter()
        rvMoreEvents.adapter = adapterMoreEvents

        detailsViewModel.additionalEvents.observe(this, Observer {
            adapterAdditionalEvents.submitList(it)
            adapterMoreEvents.submitList(it)
        })
    }

    companion object {
        const val EVENT_ID = "EVENT_ID"
    }
}
