package com.fourdev.cleanarchitecturewithrxjavahilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fourdev.cleanarchitecturewithrxjavahilt.R
import com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation.AppNavigator
import com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation.Screen
import com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map.RestaurantMapFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var appNavigator: AppNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
           appNavigator.navigateTo(Screen.Map)
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        // handle back press
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}