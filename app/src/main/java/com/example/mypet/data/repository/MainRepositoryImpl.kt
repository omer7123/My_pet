package com.example.mypet.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.mypet.data.remote.RemoteDataSource
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.Animal
import com.example.mypet.domain.entity.Breed
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.NewPet
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.TokenAuth
import com.example.mypet.domain.entity.User

class MainRepositoryImpl(private val dataSource: RemoteDataSource, private val context: Context): MainRepository {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("auth_pref", Context.MODE_PRIVATE)

    override suspend fun register(register: Register): Resource<User> {
        return dataSource.register(register)
    }

    override suspend fun auth(auth: Login): Resource<User> {
        return dataSource.auth(auth)
    }

    override suspend fun getToken(): String {
        return sharedPref.getString(TOKEN, "").toString()
    }

    override suspend fun getPets(tokenAuth: TokenAuth): Resource<List<PetItem>> {
        return dataSource.getPets(tokenAuth)
    }

    override suspend fun getAnimals(): Resource<List<Animal>> {
        return dataSource.getAnimals()
    }

    override suspend fun getBreeds(animalId: String): Resource<List<Breed>> {
        return dataSource.getBreeds(animalId)
    }

    override suspend fun addPet(pet: NewPet): Resource<PetItem> {
        return dataSource.addPet(pet)
    }

    override suspend fun saveToken(token: String) {
        sharedPref.edit().apply {
            putString(TOKEN, token)
            apply()
        }
    }

    companion object {
        private const val TOKEN = "token"
    }
}