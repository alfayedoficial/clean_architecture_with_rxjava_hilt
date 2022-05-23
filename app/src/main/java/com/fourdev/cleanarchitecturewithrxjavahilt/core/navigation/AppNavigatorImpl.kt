package com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation

import androidx.fragment.app.FragmentActivity
import com.fourdev.cleanarchitecturewithrxjavahilt.R
import com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map.RestaurantMapFragment
import com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.restaurantDetails.RestaurantFragment
import javax.inject.Inject

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:00 PM
 */
class AppNavigatorImpl @Inject constructor(private val activity : FragmentActivity) :AppNavigator {


    override fun navigateTo(screen: Screen) {
        val fragment = when(screen){
            Screen.Map -> RestaurantMapFragment()
            Screen.DETAIL -> RestaurantFragment()
            else -> {
                RestaurantMapFragment()
            }
        }

        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.homeContainer, fragment)
            .addToBackStack(fragment.javaClass.canonicalName)
            .commit()
    }
}