package com.fourdev.cleanarchitecturewithrxjavahilt.data.repo

import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.DataState
import com.fourdev.cleanarchitecturewithrxjavahilt.data.api.FourSquareApi
import com.fourdev.cleanarchitecturewithrxjavahilt.data.api.Test
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.InMemoryCash
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.LocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.RequestLocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.entity.Restaurant
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.repo.RestaurantRepo
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import java.util.ArrayList

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/23/2022 - 12:11 AM
 */
class RestaurantRepoImpl(private val api : FourSquareApi) : RestaurantRepo {

    override fun getRestaurants(locationDto: RequestLocationDto): Single<DataState<List<Restaurant>>> {
//       return api.getVenues(1075,Test(id = 1075 , name = "test_updated_4" , company_Id = 1))
//            .map {
//                val restaurants = ArrayList<Restaurant>()
//                it.response?.venues?.forEach {
//                    restaurants.add(Restaurant())
//                }
//                DataState.Success(restaurants)
//            }
        //${1.2950},${103.8583}"

        // get From cash

        val cash = InMemoryCash.get()
        val filteredData = ArrayList<Restaurant>()
        cash.forEach {
            val latLng = LatLng(it.latitude!!,it.longitude!!)
            if (locationDto.latLngBounds.contains(latLng)){
                filteredData.add(it)
            }
        }

        if (!filteredData.isNullOrEmpty()){
            return Single.just(DataState.Success(filteredData))
        }

        return api.getVenues("${locationDto.latLng.latitude},${locationDto.latLng.longitude}")
           .map {
               val restList = ArrayList<Restaurant>()
               it.fsqResults?.forEach {item ->
                   restList.add(
                   Restaurant(id = item.fsqId
                       , name = item.name
                       , city = item.location?.locality
                       , address = item.location?.address
                       , latitude = item.geocodes?.main?.latitude
                       , longitude = item.geocodes?.main?.longitude))

               }
               // Save Cash
               InMemoryCash.add(restList)

               DataState.Success(restList)
           }
    }

}