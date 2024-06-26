package com.example.mypet.data.remote

import com.example.mypet.domain.entity.Animal
import com.example.mypet.domain.entity.Auth
import com.example.mypet.domain.entity.Breed
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.NewPet
import com.example.mypet.domain.entity.NewTask
import com.example.mypet.domain.entity.Owner
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.domain.entity.PetItemUpdate
import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.Task
import com.example.mypet.domain.entity.TokenAuth
import com.example.mypet.domain.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PetApi {

    @Headers("ngrok-skip-browser-warning: true")
    @POST("/api/users/register")
    suspend fun register(
        @Body registerModel: Register
    ): Response<User>

    @Headers("ngrok-skip-browser-warning: true")
    @POST("/api/users/login")
    suspend fun auth(
        @Body authModel: Login
    ): Response<User>

    @Headers("ngrok-skip-browser-warning: true")
    @POST("/api/pets")
    suspend fun getPets(
        @Body token: TokenAuth
    ): Response<List<PetItem>>

    @Headers("ngrok-skip-browser-warning: true")
    @GET("/api/animals")
    suspend fun getAnimals(): Response<List<Animal>>

    @Headers("ngrok-skip-browser-warning: true")
    @GET("/api/breeds/{animal_id}")
    suspend fun getBreeds(@Path("animal_id") animalId: String): Response<List<Breed>>

    @Headers("ngrok-skip-browser-warning: true")
    @POST("/api/pets/add")
    suspend fun addPet(@Body pet: NewPet): Response<PetItem>

    @Headers("ngrok-skip-browser-warning: true")
    @PUT("/api/pets/update")
    suspend fun updatePet(@Body pet: PetItemUpdate): Response<PetItem>

    @Headers("ngrok-skip-browser-warning: true")
    @POST("/api/pets/{pet_id}")
    suspend fun getDetailPet(
        @Path("pet_id") id: String,
        @Body token: TokenAuth
    ): Response<PetItem>

    @Headers("ngrok-skip-browser-warning: true")
    @POST("/api/tasks")
    suspend fun getTasks(
        @Body token: TokenAuth
    ): Response<List<Task>>

    @Headers("ngrok-skip-browser-warning: true")
    @POST("/api/tasks/add")
    suspend fun createTask(
        @Body task: NewTask
    ): Response<Task>

    @Headers("ngrok-skip-browser-warning: true")
    @HTTP(method = "DELETE", path = "/api/tasks/delete/{task_id}", hasBody = true)
    suspend fun deleteTask(
        @Path("task_id") id: String,
        @Body token: TokenAuth
    ): Response<String>

}