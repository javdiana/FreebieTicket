package com.javdiana.freebleticket.view.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View.*
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.view.details.DetailsActivity
import com.javdiana.freebleticket.view.view.home.HomeFragment
import com.javdiana.freebleticket.view.view.map.MapFragment
import com.javdiana.freebleticket.view.view.tickets.TicketsFragment
import com.readystatesoftware.systembartint.SystemBarTintManager

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.updateStatusBar(tag: String) {
    when (tag) {
        DetailsActivity.DETAIL_TAG -> {
            setTransparentStatusBar(this)
        }
        HomeFragment.HOME_TAG -> {
            setWhiteStatusBar(this)
        }
        MapFragment.MAP_TAG -> {
            setWhiteStatusBar(this)
        }
        TicketsFragment.TICKETS_TAG -> {
            setTransparentStatusBar(this)
        }
    }
}

private fun setWhiteStatusBar(context: Activity) {
    context.window.apply {
        if (Build.VERSION.SDK_INT in 19..20) {
            addFlags(FLAG_TRANSLUCENT_STATUS)
            val tintManager = SystemBarTintManager(context)
            tintManager.isStatusBarTintEnabled = true
            tintManager.setTintColor(ContextCompat.getColor(context, R.color.colorWhite))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            clearFlags(FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT >= 23) {
            addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.WHITE
        }
    }
}

private fun setTransparentStatusBar(context: Activity) {
    context.window.apply {
        if (Build.VERSION.SDK_INT in 19..20) {
            FLAG_TRANSLUCENT_STATUS
        }
        if (Build.VERSION.SDK_INT >= 19) {
            context.window.decorView
                .systemUiVisibility =
                FLAG_TRANSLUCENT_STATUS or FLAG_TRANSLUCENT_STATUS
        }
        if (Build.VERSION.SDK_INT >= 23) {
            addFlags(FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}
