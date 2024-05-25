package com.example.mypet.presentation.refactorPetFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.PetEntity
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.domain.entity.PetItemUpdate
import com.example.mypet.presentation.petsFragment.PetsState
import kotlinx.coroutines.launch

class RefactorPetViewModel(private val repository: MainRepository) : ViewModel() {

    private val _screenState = MutableLiveData<RefactorPetState>()
    val screenState: LiveData<RefactorPetState> get() = _screenState


    fun getAnimals() {
        viewModelScope.launch {
            _screenState.value = RefactorPetState.Loading

            when (val animals = repository.getAnimals()) {

                is Resource.Success -> {
                    _screenState.value = RefactorPetState.SuccessAnimal(animals.data)
                }

                is Resource.Error -> _screenState.value =
                    RefactorPetState.Error(animals.msg.toString())

                Resource.Loading -> PetsState.Loading
            }
        }
    }

    fun getBreeds(id: String) {
        viewModelScope.launch {
            _screenState.value = RefactorPetState.Loading

            when (val breeds = repository.getBreeds(id)) {

                is Resource.Success -> {
                    Log.e("BreedsVM", breeds.data.toString())
                    _screenState.value = RefactorPetState.SuccessBreed(breeds.data)
                }

                is Resource.Error -> _screenState.value =
                    RefactorPetState.Error(breeds.msg.toString())

                Resource.Loading -> PetsState.Loading
            }
        }
    }

    fun updatePet(pet: PetItemUpdate) {
        viewModelScope.launch {
            val token = repository.getToken()
            pet.owner.token = token
            when (val res = repository.updatePet(pet)) {
                is Resource.Success -> {
                    Log.e("UPDATeVM", res.data.toString())
                    _screenState.value = RefactorPetState.Success(res.data)
                }

                is Resource.Error -> _screenState.value =
                    RefactorPetState.Error(res.msg.toString())

                Resource.Loading -> PetsState.Loading
            }

        }
    }
}