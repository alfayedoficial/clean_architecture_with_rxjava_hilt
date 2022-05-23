package com.fourdev.cleanarchitecturewithrxjavahilt.domain.error

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:41 PM
 */
interface ErrorHandler {

    fun getErrorHandler(throwable: Throwable): Failure
}
