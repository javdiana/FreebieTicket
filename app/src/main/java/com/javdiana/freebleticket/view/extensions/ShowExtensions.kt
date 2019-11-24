package com.javdiana.freebleticket.view.extensions

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View.*
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import android.widget.Toast
import androidx.core.content.ContextCompat
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
            tintManager.setTintColor(ContextCompat.getColor(context, R.color.white))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT >= 23) {
            addFlags(FLAG_TRANSLUCENT_STATUS)
            val tintManager = SystemBarTintManager(context)
            tintManager.isStatusBarTintEnabled = true
            tintManager.setTintColor(ContextCompat.getColor(context, R.color.white))
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
                SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            clearFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            FLAG_TRANSLUCENT_STATUS
            context.window.statusBarColor = Color.TRANSPARENT
        }
    }
}
