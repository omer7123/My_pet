package com.example.mypet.presentation.createPetFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.NewPet
import com.example.mypet.presentation.petsFragment.PetsState
import kotlinx.coroutines.launch

class CreatePetViewModel(private val repository: MainRepository) : ViewModel() {

    private val _screenState = MutableLiveData<CreatePetState>()
    val screenState: LiveData<CreatePetState> get() = _screenState


    fun addPet(pet: NewPet) {

        viewModelScope.launch {
            _screenState.value = CreatePetState.Loading
            pet.owner.token = repository.getToken()
            when (val res = repository.addPet(pet)) {

                is Resource.Success -> _screenState.value = CreatePetState.Success(res.data)
                is Resource.Error -> _screenState.value = CreatePetState.Error(res.msg.toString())
                Resource.Loading -> _screenState.value = CreatePetState.Loading
            }
        }
    }

}