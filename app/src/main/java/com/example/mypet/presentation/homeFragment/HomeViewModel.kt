package com.example.mypet.presentation.homeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.TokenAuth
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository): ViewModel() {

    private val _screenState = MutableLiveData<HomeState>()
    val screenState: LiveData<HomeState> get() = _screenState

    fun getTasks(){
        viewModelScope.launch {
            _screenState.value = HomeState.Loading
            val token = repository.getToken()
            when(val res = repository.getTasks(TokenAuth(token))){
                is Resource.Error -> _screenState.value = HomeState.Error(res.msg.toString())
                Resource.Loading -> _screenState.value = HomeState.Loading
                is Resource.Success -> _screenState.value = HomeState.Success(res.data)
            }
        }
    }

    fun deleteTask(id: String) {
        viewModelScope.launch {
            val token = TokenAuth(repository.getToken())

            when(val res = repository.deleteTask(id, token)){
                is Resource.Error -> _screenState.value = HomeState.Error(res.msg.toString())
                Resource.Loading -> _screenState.value = HomeState.Loading
                is Resource.Success -> getTasks()
            }
        }
    }
}