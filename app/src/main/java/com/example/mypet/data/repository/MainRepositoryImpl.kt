package com.example.mypet.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.mypet.data.remote.RemoteDataSource
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.Register
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