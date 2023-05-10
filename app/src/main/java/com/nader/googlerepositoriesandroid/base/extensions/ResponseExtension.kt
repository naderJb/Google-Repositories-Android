package com.nader.googlerepositoriesandroid.base.extensions

import com.google.gson.Gson
import com.nader.googlerepositoriesandroid.base.exeptions.APIException
import com.nader.googlerepositoriesandroid.base.model.ErrorModel
import retrofit2.Response


fun <R> Response<R>.dataOrException(errorMessage: String): R {
    return when (isSuccessful) {
        true -> body() ?: throw Exception(errorMessage)
        else -> throw APIException(parseErrorResponse() ?: errorMessage)
    }
}

fun <T> Response<T>.parseErrorResponse(): String? {
    val errorBody = errorBody()?.string().toString()
    return try {
        Gson().fromJson(errorBody, ErrorModel::class.java).message
    } catch (exception: Exception) {
        null
    }
}