package com.fourdev.cleanarchitecturewithrxjavahilt.domain.interactor

import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.DataState
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.LocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.RequestLocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.entity.Restaurant
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.error.ErrorHandler
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.error.Failure
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.repo.RestaurantRepo
import io.reactivex.Single
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:42 PM
 */
class GetRestaurants @Inject constructor(private var repo: RestaurantRepo) : UseCase<RequestLocationDto ,Single<DataState<List<Restaurant>>>> , ErrorHandler {

    override fun getErrorHandler(throwable: Throwable): Failure {
        return when(throwable) {
            is UnknownHostException -> Failure.NetworkConnection
            is HttpException -> {
                when(throwable.code()) {
                    400 -> Failure.ServerError.BadRequest
                    401 -> Failure.ServerError.Unauthorized
                    403 -> Failure.ServerError.Forbidden
                    404 -> Failure.ServerError.NotFound
                    405 -> Failure.ServerError.MethodNotAllowed
                    422 -> Failure.ServerError.UnProcessableEntity
                    500 -> Failure.ServerError.InternalServerError
                    else -> Failure.ServerError.Unknown
                }
            }
            else -> Failure.ServerError.Unknown
        }
    }

    override fun execute(parameter: RequestLocationDto): Single<DataState<List<Restaurant>>> {
        return repo.getRestaurants(parameter).onErrorReturn { failure ->
            DataState.Error(getErrorHandler(failure))
        }
    }
}
