package com.nader.googlerepositoriesandroid.base.model

data class APIResponse<out T>(val status: Status, val data: T?) {

    var exception: Exception? = null

    constructor(status: Status, data: T?, exception: Exception?) : this(
        status,
        data
    ) {
        this.exception = exception
    }

    companion object {
        fun <T> success(data: T?): APIResponse<T> {
            return APIResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Exception, data: T? = null): APIResponse<T> {
            return APIResponse(Status.ERROR, data, exception = exception)
        }

        fun <T> loading(): APIResponse<T> {
            return APIResponse(Status.LOADING, null, null)
        }
    }
}