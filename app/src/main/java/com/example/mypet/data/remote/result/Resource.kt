package com.example.mypet.data.remote.result

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error<out T : Any>(val msg: String?, val data: T?) : Resource<T>()
    data object Loading : Resource<Nothing>()

}