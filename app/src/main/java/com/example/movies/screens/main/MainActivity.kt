package com.example.movies.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.btm_nav_view)

        val navController = findNavController(R.id.fragment_container)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.detailFragment -> hideButtonNav()
                else -> showButtonNav()
            }
        }

        navView.itemIconTintList = null

    }

    private fun showButtonNav() {
        btm_nav_view.visibility = View.VISIBLE
    }

    private fun hideButtonNav() {
        btm_nav_view.visibility = View.GONE
    }
}