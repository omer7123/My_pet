package com.example.mypet.data.remote

import android.util.Log
import com.example.mypet.data.remote.result.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import java.lang.Exception

/**
 * Abstract base class for data sources.
 * This class provides a method to handle network requests and transform responses into Resource objects.
 */
abstract class BaseDataSource {
    data class ErrorResponse(val detail: String)

    /**
     * Executes a suspend function representing a network call and transforms the response into a Resource object.
     * @param call A suspend function representing the network call.
     * @return A Resource object representing the result of the network operation.
     */
    protected suspend fun <T : Any> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null || response.code() in 200..299) {
                    return Resource.Success(body!!)
                } else {
                    return Resource.Error(response.message(), response.body())
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                val moshi =  Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val adapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
                val errorResponse = adapter.fromJson(errorBody)

                return Resource.Error(errorResponse?.detail, null)
            }
        } catch (e: Exception) {
            return Resource.Error(e.message ?: e.toString(), null)
        }
    }
}