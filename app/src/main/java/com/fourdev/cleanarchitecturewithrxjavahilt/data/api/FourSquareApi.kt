package com.fourdev.cleanarchitecturewithrxjavahilt.data.api

import com.fourdev.cleanarchitecturewithrxjavahilt.data.apiResponse.AnotherRestaurantResponse
import com.fourdev.cleanarchitecturewithrxjavahilt.utils.Constants
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 11:52 PM
 */
interface FourSquareApi {

    @GET("search")
    fun getVenues(
        @Query("ll") latLng: String,
        @Query(Constants.QUERY) query_value: String = Constants.query_value,
        @Query(Constants.SORT) sort_value: String = Constants.sort_value,
        @Query(Constants.RADIUS) radius_value: Int = Constants.radius_value,
        @Query(Constants.LIMIT) limit_value: Int = Constants.limit_value,
    ): Single<AnotherRestaurantResponse>

//    @PUT("updatejob/{id}")
//    fun getVenues(
//        @Path("id") id: Int,
//        @Body body: Test,
//        @Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJTdXBlckFkbWluIiwianRpIjoiNWVhNmRiZGQtYTRkMy00YzU3LTljZDUtN2ViNWM5YmZiMThkIiwiZW1haWwiOiJTdXBlckFkbWluQGFsaWduLmNvbSIsInVpZCI6ImFkYTdlNjJkLTdlNjUtNDQ0MC1hN2JhLWVkYjFkMDIyMGM0YiIsImNvbXBhbnlpZCI6IjEiLCJicmFuY2hpZCI6IjEiLCJyb2xlcyI6IlN1cGVyQWRtaW4iLCJleHAiOjE2NTM0MDY3NzcsImlzcyI6IkZhY2lsaXR5TWFuYWdlbWVudCJ9.3c78C5M2924NwQcDgzmGT7PRQwO6eL01pihYg4ruuS0",
//        ): Single<AnotherRestaurantResponse>

}

data class Test(
    val id: Int,
    val name: String,
    val company_Id: Int,
)