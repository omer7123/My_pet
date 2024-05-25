package com.example.mypet.presentation.petsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.TokenAuth
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PetsViewModel(private val repository: MainRepository) : ViewModel() {

    private val _screenState = MutableLiveData<PetsState>()
    val screenState: LiveData<PetsState> get() = _screenState

    private val handler = CoroutineExceptionHandler { _, error ->
        throw error
    }

    fun getPets() {
        viewModelScope.launch {
            _screenState.value = PetsState.Loading
            val token = repository.getToken()
            val tokenAuth = TokenAuth(token)
            when (val res = repository.getPets(tokenAuth)) {

                is Resource.Success -> {
                   _screenState.value = PetsState.Success(res.data)
                }
                is Resource.Error -> _screenState.value = PetsState.Error(res.msg.toString())
                Resource.Loading -> PetsState.Loading
            }
        }
    }
}