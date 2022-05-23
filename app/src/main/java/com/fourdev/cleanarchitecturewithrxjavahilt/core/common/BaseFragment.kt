package com.fourdev.cleanarchitecturewithrxjavahilt.core.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import permissions.dispatcher.RuntimePermissions

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 7:41 PM
 */
open class BaseFragment : Fragment() {

    private var locationCallback: LocationCallback? = null
    private var locationRequest: LocationRequest? = null
    private var fusedLocationClient : FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun setupLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getRootActivity())
    }

    fun getRootActivity() = activity as Activity

    @SuppressLint("MissingPermission")
    fun getCurrentLastKnownLocation(onLocationAvailable : (Location) -> Unit) {
        fusedLocationClient?.lastLocation?.addOnSuccessListener { location : Location? ->
            if (location != null) {
                onLocationAvailable(location)
            }else{
               createLocationRequest(onLocationAvailable)
            }
        }
    }

    private fun createLocationRequest(onLocationAvailable: (Location) -> Unit) {
       locationRequest = LocationRequest.create().apply {
           interval = 5000
           fastestInterval = 5000
           priority = LocationRequest.PRIORITY_HIGH_ACCURACY
       }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    onLocationAvailable(location)
                }
            }
        }
        requestLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates(){
        if (locationRequest != null && locationCallback != null) {
            fusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        }
    }

    private fun stopLocationUpdates(){
        if (fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient!!.removeLocationUpdates(locationCallback)
        }
    }

    override fun onResume() {
        super.onResume()
        requestLocationUpdates()
    }

    override fun onStop() {
        super.onStop()
        stopLocationUpdates()
    }

    fun isLocationEnabled() : Boolean {
        val locationManager = getRootActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
}