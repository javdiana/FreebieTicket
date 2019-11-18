package com.javdiana.freebleticket.view.view.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.R.layout
import com.javdiana.freebleticket.view.extensions.formatToString
import com.javdiana.freebleticket.view.extensions.toast
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.view.Constants
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.view_more_details_dialog.*
import kotlinx.android.synthetic.main.view_more_details_sheet.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailsActivity : AppCompatActivity() {
    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_details)

        initStatusBar()
        initEvent()
    }

    private fun initStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun initEvent() {
        detailsViewModel.id = intent.getLongExtra(Constants.EXTRA_ID, 0L)
        detailsViewModel.getEvent()

        detailsViewModel.event.observe(this, Observer {
            initViews(detailsViewModel.event.value)
        })
    }

    private fun initViews(event: Event?) {
        if (event != null) {
            detailsActivity.costResultDetail.text = String.format(resources.getString(R.string.show_2_string), event.costLow, event.costHigh)
            butTicketsDetails.setOnClickListener {
                this.toast(resources.getString(R.string.tickets_bought_message))
                finish()
            }

            titleDetails.text = event.name
            tvDateDetails.text = (event.date).formatToString()
            tvLocationDetails.text = event.place
            tvTypeDetails.text = event.type
            tvCostDetails.text = String.format(resources.getString(R.string.show_2_string), event.costLow, event.costHigh)
            tvPlaceDetails.text = event.place
            sourceDetail.text = event.source
            /*todo detailsDetails.text = event.details
            updatesDetails.text = event.updates

            //frLocationDetails


            performersDetails.layoutManager = LinearLayoutManager(baseContext)
            val adapter = UserAdapter()
            adapter.submitList(event.performers)
            performersDetails.adapter = adapter
            organizersDetails.layoutManager = LinearLayoutManager(baseContext)
            adapter.submitList(event.organizers)
            organizersDetails.adapter = adapter*/

        }


        imageBackDetails.setOnClickListener { finish() }
        imageLikeDetails.setOnClickListener { }
        imageUploadDetails.setOnClickListener { }


    }
}
