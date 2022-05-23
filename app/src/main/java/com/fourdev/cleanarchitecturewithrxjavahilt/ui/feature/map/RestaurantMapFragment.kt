package com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.fourdev.cleanarchitecturewithrxjavahilt.R
import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.BaseFragment
import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.DataState
import com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation.AppNavigator
import com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation.Screen
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.LocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map.viewModel.MapViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import permissions.dispatcher.*
import timber.log.Timber
import javax.inject.Inject

@RuntimePermissions
@AndroidEntryPoint
class RestaurantMapFragment : BaseFragment() , GoogleMap.OnMarkerClickListener {

    @Inject
    lateinit var appNavigator: AppNavigator

    private val mViewModel: MapViewModel by viewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap.setOnMarkerClickListener(this)
        observerRestaurants()
    }

    private fun observerRestaurants() {
            mViewModel.restaurantsState.observe(viewLifecycleOwner) {
                when(it) {
                    is DataState.Success->{}
                    is DataState.Error -> {}
                    is DataState.Loading -> {}
                }
            }
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
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurantt_map, container, false)
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
                Timber.e("available location lat , long : %s , %s", it.latitude, it.longitude)
                // cal foursquare api
                mViewModel.getRestaurants(LocationDto(it.latitude, it.longitude))
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    // Mark -*- handle Permissions

    override fun onMarkerClick(p0: Marker): Boolean {
        appNavigator.navigateTo(Screen.DETAIL)
        return false
    }

}