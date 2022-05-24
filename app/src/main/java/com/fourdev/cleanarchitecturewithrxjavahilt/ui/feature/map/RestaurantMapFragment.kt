package com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.fourdev.cleanarchitecturewithrxjavahilt.R
import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.BaseFragment
import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.DataState
import com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation.AppNavigator
import com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation.Screen
import com.fourdev.cleanarchitecturewithrxjavahilt.databinding.FragmentRestauranttMapBinding
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.RequestLocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.entity.Restaurant
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.error.Failure
import com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map.drag.IDragCallback
import com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map.viewModel.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import permissions.dispatcher.*
import timber.log.Timber
import javax.inject.Inject

@RuntimePermissions
@AndroidEntryPoint
class RestaurantMapFragment : BaseFragment() , GoogleMap.OnMarkerClickListener , IDragCallback {

    @Inject
    lateinit var appNavigator: AppNavigator

    private val mViewModel: MapViewModel by viewModels()
    private var mMap: GoogleMap? = null
    private var _dataBinding: FragmentRestauranttMapBinding? = null
    private val dataBinding get() = _dataBinding!!

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        googleMap.setMinZoomPreference(12f)
        googleMap.setOnMarkerClickListener(this)
        observerRestaurants()
    }

    private val locationSettingScreen = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        getCurrentLocation()
    }

    private val applicationSettingsScreen = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        getCurrentLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _dataBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_restaurantt_map , container , false)
        dataBinding.lyDraggable.setDragCallback(this)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        getCurrentLocationWithPermissionCheck()
    }

    // Mark -*- handle Permissions
    // NeedsPermission method is called when the user has not granted the permission
    @NeedsPermission(android.Manifest.permission.ACCESS_FINE_LOCATION , android.Manifest.permission.ACCESS_COARSE_LOCATION)
    fun getCurrentLocation() {
        if (isLocationEnabled()) {
            // Do the task needing access to the location
            getCurrentLastKnownLocation{
                // Do something with the location
                Timber.e("available location lat , long : %s,%s", it.latitude,it.longitude)
                // cal foursquare api
                val currentLocation = LatLng(it.latitude, it.longitude)
                val currentBounds = mMap?.projection?.visibleRegion?.latLngBounds
                if (currentBounds != null)
                    mViewModel.getRestaurants(RequestLocationDto(currentLocation , currentBounds))
            }
        }else{
            // Show dialog to enable location
            MaterialAlertDialogBuilder(getRootActivity())
                .setTitle("Enable Location")
                .setMessage("Your location is disabled. Please enable it in settings.")
                .setPositiveButton("Location Settings") { dialog, _ ->
                    //TODO : Navigate to location settings
                    onSettingScreen()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    // Do nothing
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun onSettingScreen() {
        locationSettingScreen.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    // OnShowRationale method is called if the user has denied the permission before
    @OnShowRationale(android.Manifest.permission.ACCESS_FINE_LOCATION , android.Manifest.permission.ACCESS_COARSE_LOCATION)
    fun onRationaleAskLocation(request : PermissionRequest) {
        // Show the rationale
        MaterialAlertDialogBuilder(getRootActivity())
            .setTitle("Location Permission")
            .setMessage("Location permission is needed to get your current location.")
            .setPositiveButton("Ok") { dialog, _ ->
                //TODO : Navigate to location settings
                request.proceed()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Do nothing
                request.cancel()
                dialog.dismiss()
            }
            .show()
    }

    // OnPermissionDenied method is called if the user has denied the permission
    @OnPermissionDenied(android.Manifest.permission.ACCESS_FINE_LOCATION , android.Manifest.permission.ACCESS_COARSE_LOCATION)
    fun onDeniedAskLocation() {
        Toast.makeText(getRootActivity(), "Location permission denied", Toast.LENGTH_SHORT).show()
    }

    // OnNeverAskAgain method is called if the user has denied the permission and checked "Never ask again"
    @OnNeverAskAgain(android.Manifest.permission.ACCESS_FINE_LOCATION , android.Manifest.permission.ACCESS_COARSE_LOCATION)
    fun onNeverAskLocation() {
        val onApplicationSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        onApplicationSettings.data = Uri.parse("package:${getRootActivity().packageName}")
        applicationSettingsScreen.launch(onApplicationSettings)
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    // Mark -*- handle Permissions

    override fun onMarkerClick(p0: Marker): Boolean {
        appNavigator.navigateTo(Screen.DETAIL)
        return false
    }

    private fun observerRestaurants() {
        mViewModel.restaurantsState.observe(viewLifecycleOwner) {
            when(it) {
                is DataState.Success->{
                    handlingLoading(false)
                    renderMarkers(it.data)}
                is DataState.Error -> {
                    handlingLoading(false)
                    val message = when (it.exception) {
                        is Failure.NetworkConnection -> {
                            "Network Error"
                        }
                        is Failure.ServerError.NotFound -> {
                            "NotFound"
                        }
                        is Failure.ServerError.BadRequest -> {
                            "BadRequest"
                        }
                        else -> {
                            "Something went wrong"
                        }
                    }

                    handleError(message)
                }
                is DataState.Loading -> {handlingLoading(true)}
            }
        }
    }

    private fun renderMarkers(venuesItems :List<Restaurant>){
        val newVenues = mViewModel.getNewRestaurants(venuesItems)
        newVenues.forEach { venue ->
            val loc = LatLng(venue.latitude!!, venue.longitude!!)
            val marker = mMap?.addMarker(MarkerOptions().position(loc).title(venue.name))
            mMap?.moveCamera(CameraUpdateFactory.newLatLng(loc))
            mViewModel.markers[marker]
        }
    }


    private fun handlingLoading(status : Boolean){
        if(status){
            dataBinding.progressBar.visibility = View.VISIBLE
        }else{
            dataBinding.progressBar.visibility = View.GONE
        }
    }


    private fun handleError(error: String){
        Snackbar.make(dataBinding.root, error, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _dataBinding = null
    }

    override fun onDrag() {
        val currentLocation = mMap?.cameraPosition?.target
        val currentBounds = mMap?.projection?.visibleRegion?.latLngBounds
        mViewModel.resetRestaurantsState()
        if (currentBounds != null && currentLocation != null)
            mViewModel.getRestaurants(RequestLocationDto(currentLocation , currentBounds))
    }


}