package com.example.mypet.presentation.createTaskFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.NewTask
import com.example.mypet.domain.entity.TokenAuth
import com.example.mypet.presentation.petsFragment.PetsState
import kotlinx.coroutines.launch

class CreateTaskViewModel(private val repository: MainRepository) : ViewModel() {

    private val _stateScreen: MutableLiveData<CreateTaskState> = MutableLiveData<CreateTaskState>()
    val stateScreen: LiveData<CreateTaskState> = _stateScreen

    fun addTask(task: NewTask) {
        viewModelScope.launch {
            task.owner.token = repository.getToken()
            when(val res = repository.createTask(task)){
                is Resource.Error -> _stateScreen.value = CreateTaskState.Error(res.msg.toString())
                Resource.Loading -> _stateScreen.value = CreateTaskState.Loading
                is Resource.Success -> _stateScreen.value = CreateTaskState.Success(res.data)
            }
        }
    }

    fun getPets() {
        viewModelScope.launch {
            _stateScreen.value = CreateTaskState.Loading
            val token = repository.getToken()
            val tokenAuth = TokenAuth(token)
            when (val res = repository.getPets(tokenAuth)) {

                is Resource.Success -> {
                    _stateScreen.value = CreateTaskState.SuccessPets(res.data)
                }
                is Resource.Error -> _stateScreen.value = CreateTaskState.Error(res.msg.toString())
                Resource.Loading -> PetsState.Loading
            }
        }
    }
}