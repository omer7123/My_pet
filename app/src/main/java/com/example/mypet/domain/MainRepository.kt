package com.example.mypet.domain

import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.entity.Animal
import com.example.mypet.domain.entity.Breed
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.NewPet
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.domain.entity.PetItemUpdate
import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.TokenAuth
import com.example.mypet.domain.entity.User

interface MainRepository {
    suspend fun register(register: Register): Resource<User>
    suspend fun auth(auth: Login): Resource<User>
    suspend fun saveToken(token: String)
    suspend fun getToken(): String
    suspend fun getPets(tokenAuth: TokenAuth): Resource<List<PetItem>>
    suspend fun getAnimals(): Resource<List<Animal>>
    suspend fun getBreeds(animalId: String): Resource<List<Breed>>
    suspend fun addPet(pet: NewPet): Resource<PetItem>
    suspend fun updatePet(pet: PetItemUpdate): Resource<PetItem>
    suspend fun getDetailPet(id: String, tokenAuth: TokenAuth): Resource<PetItem>
}