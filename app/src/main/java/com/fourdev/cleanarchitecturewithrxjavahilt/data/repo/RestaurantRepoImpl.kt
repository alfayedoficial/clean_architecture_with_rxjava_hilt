package com.fourdev.cleanarchitecturewithrxjavahilt.data.repo

import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.DataState
import com.fourdev.cleanarchitecturewithrxjavahilt.data.api.FourSquareApi
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.LocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.entity.Restaurant
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.repo.RestaurantRepo
import io.reactivex.Single
import java.util.ArrayList

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/23/2022 - 12:11 AM
 */
class RestaurantRepoImpl(private val api : FourSquareApi) : RestaurantRepo {

    override fun getRestaurants(locationDto: LocationDto): Single<DataState<List<Restaurant>>> {
       return api.getVenues("${locationDto.lat},${locationDto.lng}")
           .map {
               val restList = ArrayList<Restaurant>()
               it.response?.venues?.forEach {item ->
                   restList.add(
                   Restaurant(id = item?.id
                       , name = item?.name
                       , city = item?.location?.city
                       , address = item?.location?.address
                       , latitude = item?.location?.lat
                       , longitude = item?.location?.lng))

               }
               DataState.Success(restList)
           }
    }
}