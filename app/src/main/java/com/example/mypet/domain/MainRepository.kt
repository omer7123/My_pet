package com.example.mypet.domain

import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.User

interface MainRepository {
    suspend fun register(register: Register): Resource<User>
    suspend fun auth(auth: Login): Resource<User>
    suspend fun saveToken(token: String)
    suspend fun getToken() : String
}