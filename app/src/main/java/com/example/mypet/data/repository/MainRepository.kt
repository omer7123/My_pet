package com.example.mypet.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mypet.data.remote.RemoteDataSource
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.User
import kotlinx.coroutines.Dispatchers

class MainRepository(private val dataSource: RemoteDataSource) {

    fun register(register: Register): LiveData<Resource<User>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val resp = dataSource.register(register)
        emit(resp)
    }
}