package com.example.mypet.data.remote

import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.User


class RemoteDataSource(val petApi: PetApi) : BaseDataSource() {
    suspend fun register(register: Register) = getResult {
        petApi.register(register)
    }

    suspend fun auth(auth: Login): Resource<User> = getResult {
        petApi.auth(auth)
    }
}