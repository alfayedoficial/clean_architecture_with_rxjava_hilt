package com.fourdev.cleanarchitecturewithrxjavahilt.domain.repo

import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.DataState
import com.fourdev.cleanarchitecturewithrxjavahilt.data.api.Test
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.LocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.RequestLocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.entity.Restaurant
import io.reactivex.Single

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:39 PM
 */
interface RestaurantRepo {

    fun getRestaurants(locationDto : RequestLocationDto):Single<DataState<List<Restaurant>>>
}