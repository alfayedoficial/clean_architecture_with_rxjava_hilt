package com.fourdev.cleanarchitecturewithrxjavahilt.core.common

import com.fourdev.cleanarchitecturewithrxjavahilt.domain.error.Failure

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:33 PM
 */
sealed class DataState<out T> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Failure) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
