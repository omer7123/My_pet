package com.example.mypet.data.remote

import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.entity.Animal
import com.example.mypet.domain.entity.Breed
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.NewPet
import com.example.mypet.domain.entity.NewTask
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.domain.entity.PetItemUpdate
import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.Task
import com.example.mypet.domain.entity.TokenAuth
import com.example.mypet.domain.entity.User


class RemoteDataSource(val petApi: PetApi) : BaseDataSource() {
    suspend fun register(register: Register) = getResult {
        petApi.register(register)
    }

    suspend fun auth(auth: Login): Resource<User> = getResult {
        petApi.auth(auth)
    }

    suspend fun getPets(tokenAuth: TokenAuth): Resource<List<PetItem>> = getResult {
        petApi.getPets(tokenAuth)
    }

    suspend fun getAnimals(): Resource<List<Animal>> = getResult {
        petApi.getAnimals()
    }

    suspend fun getBreeds(animalId: String): Resource<List<Breed>> = getResult {
        petApi.getBreeds(animalId)
    }

    suspend fun addPet(pet: NewPet): Resource<PetItem> = getResult {
        petApi.addPet(pet)
    }

    suspend fun updatePet(pet: PetItemUpdate): Resource<PetItem> = getResult {
        petApi.updatePet(pet)
    }

    suspend fun getDetailPet(id: String, tokenAuth: TokenAuth): Resource<PetItem> = getResult {
        petApi.getDetailPet(id, tokenAuth)
    }

    suspend fun getTasks(tokenAuth: TokenAuth): Resource<List<Task>> = getResult{
        petApi.getTasks(tokenAuth)
    }

    suspend fun createTask(task: NewTask): Resource<Task> = getResult{
        petApi.createTask(task)
    }

    suspend fun deleteTask(id: String, token: TokenAuth): Resource<String> = getResult{
        petApi.deleteTask(id, token)
    }
}