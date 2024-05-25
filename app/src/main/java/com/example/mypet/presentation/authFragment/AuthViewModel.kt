package com.example.mypet.presentation.authFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.Login
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: MainRepository) : ViewModel() {

    private val _screenState = MutableLiveData<AuthState>()
    val screenState: LiveData<AuthState> get() = _screenState

    private val handler = CoroutineExceptionHandler { _, error ->
        throw error
    }
    fun auth(data: Login) {
        viewModelScope.launch {
            _screenState.value = AuthState.Loading
            when(val res = repository.auth(data)){
               is Resource.Success->{
                   saveToken(res.data.token)
                   _screenState.value = AuthState.Success(res.data)
               }
                is Resource.Error -> _screenState.value = AuthState.Error(res.msg.toString())
                Resource.Loading -> AuthState.Loading
            }
        }
    }

    private fun saveToken(token: String) {
        viewModelScope.launch(handler) {
            repository.saveToken(token)
        }
    }
}