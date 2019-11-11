package com.javdiana.freebleticket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.mainHostFragment)
        setUpBottomNav(navController)
    }


    private fun setUpBottomNav(navController: NavController) {
        bottomNavigationView.setupWithNavController(navController)
    }
}
