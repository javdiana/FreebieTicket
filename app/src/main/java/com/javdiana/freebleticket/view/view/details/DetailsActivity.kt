package com.javdiana.freebleticket.view.view.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.javdiana.freebleticket.R.layout
import com.javdiana.freebleticket.view.model.entity.Event
import com.javdiana.freebleticket.view.utils.DateUtil
import com.javdiana.freebleticket.view.view.Constants
import com.javdiana.freebleticket.view.view.details.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.view_more_details_dialog.*
import kotlinx.android.synthetic.main.view_more_details_sheet.*
import kotlinx.android.synthetic.main.view_more_details_sheet.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailsActivity : AppCompatActivity() {
    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_details)

        configStatusBar()
        configBottomSheetBehavior()

        val id = savedInstanceState?.getLong(Constants.EXTRA_ID) ?: intent.getLongExtra(Constants.EXTRA_ID,0L)
        detailsViewModel.id = id
        detailsViewModel.getEvent()
        val event: Event? = detailsViewModel.event.value
        initViews(event)

    }

    private fun initViews(event: Event?) {
        if(event !=null) {
            detailsActivity.costResultDetail.text = "€${event.costLow}-${event.costHigh}"
            butTicketsDetails.setOnClickListener {
                Toast.makeText(baseContext, "Tickets bought!", Toast.LENGTH_SHORT).show()
                finish()
            }

            titleDetails.text = event.name
            tvDateDetails.text = DateUtil.dateToString(event.date)
            tvLocationDetails.text = event.place
            tvTypeDetails.text = event.type
            tvCostDetails.text = "€${event.costLow}-${event.costHigh}"
            tvPlaceDetails.text = event.place
            sourceDetail.text = event.source
//            detailsDetails.text = event.details
//            updatesDetails.text = event.updates
//
//            //frLocationDetails
//
//
//            performersDetails.layoutManager = LinearLayoutManager(baseContext)
//            val adapter = UserAdapter()
//            adapter.submitList(event.performers)
//            performersDetails.adapter = adapter
//            organizersDetails.layoutManager = LinearLayoutManager(baseContext)
//            adapter.submitList(event.organizers)
//            organizersDetails.adapter = adapter

        }


        imageBackDetails.setOnClickListener { finish() }
        imageLikeDetails.setOnClickListener { }
        imageUploadDetails.setOnClickListener { }


    }

    private fun configStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun configBottomSheetBehavior(){
//       val viewMoreDetails = BottomSheetBehavior.from<LinearLayout>(viewMoreDetails)
//        viewMoreDetails.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//                    }
//                    BottomSheetBehavior.STATE_EXPANDED ->
//                        viewMoreDetails.text = "Close Bottom Sheet"
//                    BottomSheetBehavior.STATE_COLLAPSED ->
//                        viewMoreDetails.text = "Expand Bottom Sheet"
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//                    }
//                    BottomSheetBehavior.STATE_SETTLING -> {
//                    }
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//            }
//        })
    }

}
