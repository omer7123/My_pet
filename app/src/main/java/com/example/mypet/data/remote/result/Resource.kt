package com.example.mypet.data.remote.result

import com.example.taskwithusers.core.network.result.Status

data class Resource<T>(val status: Status, val data: T?, val msg: String?, val code: Int?) {

    companion object{

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msg: String?, data: T?, code: Int?): Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null, null)
        }
    }
}