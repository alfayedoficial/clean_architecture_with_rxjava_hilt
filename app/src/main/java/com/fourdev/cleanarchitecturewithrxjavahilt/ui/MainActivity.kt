package com.fourdev.cleanarchitecturewithrxjavahilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fourdev.cleanarchitecturewithrxjavahilt.R
import com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map.RestaurantMapFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.homeContainer, RestaurantMapFragment())
                .commitNow()
        }

    }
}