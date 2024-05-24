package com.example.mypet.data.remote

import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PetApi {

    @Headers("ngrok-skip-browser-warning: true")
    @POST("/api/users/register")
    suspend fun register(
        @Body registerModel: Register
    ): Response<User>

}