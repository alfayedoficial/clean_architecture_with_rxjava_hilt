package com.fourdev.cleanarchitecturewithrxjavahilt.domain.interactor

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:31 PM
 */
interface UseCase<T,R> {

    fun execute(parameter: T): R
}

// T : input parameter
// R : return parameter