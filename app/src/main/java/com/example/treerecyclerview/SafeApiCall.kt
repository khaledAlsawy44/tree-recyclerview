package com.example.treerecyclerview

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException


suspend fun <T> safe(
    call: suspend () -> Either<AppFailure, T>
): Either<AppFailure, T> {
    return try {
        return call()
    } catch (e: Exception) {
        e.printStackTrace()
        e.toAppFailure().left()
    }
}

fun Throwable.toAppFailure(): AppFailure {
    return when (this) {
        is HttpException -> {
            when (code()) {
                in 300 until 400 -> ApiErrors.Unauthorized("Not Authorized.")
                in 400 until 500 -> ValidationErrors.Validation("Data not valid.")
                in 500 until 600 -> ApiErrors.ServerError("Server is busy. Please try again later.")
                else -> GeneralErrors.General("Something went wrong. Please try again later.")
            }
        }
        is UnknownHostException -> ApiErrors.NetworkConnection("Connection Error, Please Check your network connection")
        is IOException -> ApiErrors.NetworkConnection("Check your internet connection.")
        is JsonDataException -> ApiErrors.ServerError("Data does not match")
        else -> GeneralErrors.General("Something went wrong. Please try again later.")
    }
}


fun <T, R> Response<T>.mapResponseData(transform: (T) -> R?): Either<AppFailure, R> {
    val data = body()
    if (data != null) {
        return transform(data)?.right() ?: return ValidationErrors.Mapping("Data not valid.").left()
    }

    return ApiErrors.ServerError("Something went wrong. Please try again later.").left()
}