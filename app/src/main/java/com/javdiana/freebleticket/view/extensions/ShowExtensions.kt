package com.javdiana.freebleticket.view.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.View.*
import android.view.WindowManager.LayoutParams.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.javdiana.freebleticket.R
import com.readystatesoftware.systembartint.SystemBarTintManager

const val TAG_ACTIVITY_DETAIL = "TAG_ACTIVITY_DETAIL"
const val TAG_HOME_MAP = "TAG_HOME_MAP"

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.updateStatusBar(tag: String) {
    when (tag) {
        TAG_ACTIVITY_DETAIL -> {
            setTransparentStatusBar(this)
        }
        TAG_HOME_MAP -> {
            setWhiteStatusBar(this)
        }
    }
}

private fun setWhiteStatusBar(context: Activity) {
    context.window.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
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
            clearFlags(FLAG_TRANSLUCENT_STATUS)
            addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            val tintManager = SystemBarTintManager(context)
            tintManager.isStatusBarTintEnabled = true
            tintManager.setTintColor(Color.WHITE)
            decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
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
            clearFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            addFlags(FLAG_TRANSLUCENT_STATUS)
            context.window.statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}
