package com.javdiana.freebleticket.view.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.javdiana.freebleticket.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        val navController = Navigation.findNavController(this, R.id.mainHostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

}
