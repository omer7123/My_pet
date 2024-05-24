package com.example.mypet.data.remote

import com.example.mypet.data.remote.result.Resource
import retrofit2.Response
import java.lang.Exception

/**
 * Abstract base class for data sources.
 * This class provides a method to handle network requests and transform responses into Resource objects.
 */
abstract class BaseDataSource {

    /**
     * Executes a suspend function representing a network call and transforms the response into a Resource object.
     * @param call A suspend function representing the network call.
     * @return A Resource object representing the result of the network operation.
     */
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null || response.code() in 200..299) {
                    return Resource.success(body)
                } else {
                    return Resource.error(response.message(), response.body(), response.code())
                }

            }
        } catch (e: Exception) {
            return Resource.error(e.message ?: e.toString(), null, 429)
        }
        return Resource.error(null, null, 429)
    }
}