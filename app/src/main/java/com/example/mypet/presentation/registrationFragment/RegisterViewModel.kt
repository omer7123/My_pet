package com.example.mypet.presentation.registrationFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.Register
import com.example.mypet.presentation.authFragment.AuthState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: MainRepository) : ViewModel() {

    private val _screenState = MutableLiveData<RegisterState>()
    val screenState: LiveData<RegisterState> get() = _screenState

    private val handler = CoroutineExceptionHandler { _, error ->
        throw error
    }

    fun register(data: Register) {
        viewModelScope.launch {
            _screenState.value = RegisterState.Loading
            when (val res = repository.register(data)) {
                is Resource.Success -> {
                    saveToken(res.data.token)
                    _screenState.value = RegisterState.Success(res.data)
                }

                is Resource.Error -> _screenState.value = RegisterState.Error(res.msg.toString())
                Resource.Loading -> RegisterState.Loading
            }
        }
    }
    fun auth(data: Login) {
        viewModelScope.launch {
            _screenState.value = RegisterState.Loading
            when(val res = repository.auth(data)){
                is Resource.Success->{
                    saveToken(res.data.token)
                    Log.e("TOKEN", res.data.token)
                    _screenState.value = RegisterState.Success(res.data)

                }
                is Resource.Error -> _screenState.value = RegisterState.Error(res.msg.toString())
                Resource.Loading -> AuthState.Loading
            }
        }
    }

    private fun saveToken(token: String) {
        viewModelScope.launch(handler) {
            repository.saveToken(token)
        }
    }

    fun getToken(): String {
        var token = ""
        viewModelScope.launch {
            token = repository.getToken()
        }
        return token
    }
}