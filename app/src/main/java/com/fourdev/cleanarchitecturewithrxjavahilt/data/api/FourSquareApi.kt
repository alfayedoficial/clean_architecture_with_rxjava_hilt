package com.fourdev.cleanarchitecturewithrxjavahilt.data.api

import com.fourdev.cleanarchitecturewithrxjavahilt.data.apiResponse.RestaurantResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 11:52 PM
 */
interface FourSquareApi {

    @GET("venues/search")
    fun getVenues(@Query("ll") latLng: String, ): Single<RestaurantResponse>
}