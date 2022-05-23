package com.fourdev.cleanarchitecturewithrxjavahilt.domain.error

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:36 PM
 */
sealed class Failure {
    object NetworkConnection : Failure()
    sealed class ServerError : Failure(){
        object NotFound : ServerError()
        object BadRequest : ServerError()
        object Unauthorized : ServerError()
        object Forbidden : ServerError()
        object MethodNotAllowed : ServerError()
        object UnProcessableEntity : ServerError()
        object InternalServerError : ServerError()
        object Unknown : ServerError()
    }

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
