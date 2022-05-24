package com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

data class RequestLocationDto(val latLng: LatLng , val latLngBounds: LatLngBounds)