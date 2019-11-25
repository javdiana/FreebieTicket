package com.javdiana.freebleticket.view.view.details

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.extensions.*
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
    private val setPermissionsLocation: () -> Unit = {
        this.setLocationPermissions()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val toolbar = findViewById<Toolbar>(R.id.tbShowDetails)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        initEvent()

        detailsViewModel.getAdditionalEvents()
        this.updateStatusBar(TAG_ACTIVITY_DETAIL)
    }

    override fun onResume() {
        super.onResume()
        this.updateStatusBar(TAG_ACTIVITY_DETAIL)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        this.updateStatusBar(TAG_ACTIVITY_DETAIL)
    }


    private fun initEvent() {
        val id = intent.getLongExtra(EVENT_ID, 0L)
        detailsViewModel.getEvent(id)

        detailsViewModel.event.observe(this, Observer {
            initViews(it)
        })
    }

    private fun initViews(event: Event) {
        initColapsingToolBar(event.name)

        detailsActivity.costResultDetail.text = String.format(
            resources.getString(R.string.show_2_string),
            event.costLow,
            event.costHigh
        )
        butTicketsDetails.setOnClickListener {
            this.toast(resources.getString(R.string.tickets_bought_message))
            finish()
        }

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

        detailsDetails.text = String.format(event.details + "\n\n")
        updatesDetails.text = String.format(event.updates + "\n\n")

        tvDateUpdates.text = event.date.getMonthDayYear()
        tvPlaceDetailsMain.text = event.place
        tvAddressCountryDetails.text = String.format("%s, %s", event.address, event.country)

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapLocationDetails) as SupportMapFragment
        val map = ConfigMap(this, mapFragment, arrayListOf(event))
        imageMyPositionDetails.setOnClickListener { map.setMyLocation(setPermissionsLocation) }

        initUsers(event.performers)
        initUsers(event.organizers)

        initAdditionalEvents()
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

    private fun initColapsingToolBar(title: String) {
        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                collapsingToolbar.title = title
                changeColorLayoutButtons(true)
            } else {
                collapsingToolbar.title = ""
                changeColorLayoutButtons(false)
            }
        })
    }

    private fun changeColorLayoutButtons(isExpandedBar: Boolean) {
        if (isExpandedBar) {
            linearLayout3.background = resources.getDrawable(R.color.colorWhite)
            viewLine.background = resources.getDrawable(R.color.colorWhite)
            buyTicketsDetail.background = resources.getDrawable(R.color.colorWhite)
            costResultDetail.setTextColor(resources.getColor(R.color.colorBackgroundDark))
            sourceDetail.setTextColor(resources.getColor(R.color.colorBackgroundDark))
            butTicketsDetails.background = resources.getDrawable(R.drawable.button_buy_tickets_pink)
            butTicketsDetails.setTextColor(resources.getColor(R.color.colorWhite))
        } else {
            linearLayout3.background = resources.getDrawable(R.color.colorBackgroundDark)
            viewLine.background = resources.getDrawable(R.color.colorBackgroundDark)
            buyTicketsDetail.background = resources.getDrawable(R.color.colorBackgroundDark)
            costResultDetail.setTextColor(resources.getColor(R.color.colorWhite))
            sourceDetail.setTextColor(resources.getColor(R.color.colorWhite))
            butTicketsDetails.background = resources.getDrawable(R.drawable.button_buy_tickets_white)
            butTicketsDetails.setTextColor(resources.getColor(R.color.colorPink))
        }
    }

    companion object {
        const val EVENT_ID = "EVENT_ID"
    }
}
