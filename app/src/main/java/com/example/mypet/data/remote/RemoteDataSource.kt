package com.example.mypet.data.remote

import com.example.mypet.domain.entity.Register




class RemoteDataSource(val petApi: PetApi) : BaseDataSource() {
    suspend fun register(register: Register) = getResult {
        petApi.register(register)
    }
}