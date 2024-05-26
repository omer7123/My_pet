package com.example.mypet.presentation.detailTaskFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.TokenAuth
import kotlinx.coroutines.launch

class DetailTaskViewModel(private val repository: MainRepository): ViewModel() {

    private val _screenState: MutableLiveData<DetailTaskState> = MutableLiveData()
    val screenState: LiveData<DetailTaskState> = _screenState

    fun getTask(id: String){
        viewModelScope.launch {
            val token = TokenAuth(repository.getToken())
            when(val res = repository.getTask(id, token)){

            }
        }
    }
}