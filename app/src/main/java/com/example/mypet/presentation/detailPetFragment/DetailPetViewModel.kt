package com.example.mypet.presentation.detailPetFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.data.remote.result.Resource
import com.example.mypet.domain.MainRepository
import com.example.mypet.domain.entity.Animal
import com.example.mypet.domain.entity.Breed
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.domain.entity.TokenAuth
import kotlinx.coroutines.launch

class DetailPetViewModel(private val repository: MainRepository) : ViewModel() {
    private val _screenState = MutableLiveData<DetailPetState>()
    val screenState: LiveData<DetailPetState> get() = _screenState

    fun getDetailPet(id: String) {
        viewModelScope.launch {
            _screenState.value = DetailPetState.Loading
            when (val res = repository.getDetailPet(id, TokenAuth(repository.getToken()))) {
                is Resource.Error -> _screenState.value = DetailPetState.Error(res.msg.toString())
                Resource.Loading -> _screenState.value = DetailPetState.Loading
                is Resource.Success -> getBreeds(res.data.animal_id, res.data.breed_id, res.data)
            }
        }
    }

    private fun getBreeds(idAnimal: String, idBreed: String, pet: PetItem) {
        viewModelScope.launch {
            _screenState.value = DetailPetState.Loading

            when (val animals = repository.getAnimals()) {

                is Resource.Success -> {
                    val animal = findAnimalById(idAnimal, animals.data)


                    if (animal != null) {
                        when (val breeds = repository.getBreeds(animal.id)) {

                            is Resource.Error -> _screenState.value =
                                DetailPetState.Error(breeds.msg.toString())

                            Resource.Loading -> _screenState.value = DetailPetState.Loading
                            is Resource.Success -> {
                                val breed: Breed? = findBreedById(idBreed, breeds.data)
                                _screenState.value =
                                    animal.let {
                                        DetailPetState.Content(
                                            it.name,
                                            breed = breed?.name ?: "",
                                            pet
                                        )
                                    }
                            }
                        }
                    } else {
                        _screenState.value = DetailPetState.Content(animal?.name ?: "", "", pet)
                    }
                }

                is Resource.Error -> _screenState.value =
                    DetailPetState.Error(animals.msg.toString())

                Resource.Loading -> DetailPetState.Loading
            }
        }
    }

    private fun findAnimalById(idAnimal: String, animals: List<Animal>): Animal? {
        animals.forEach {
            if (idAnimal == it.id) return it
        }
        return null
    }

    private fun findBreedById(idBreed: String, data: List<Breed>): Breed? {
        data.forEach {
            if (idBreed == it.id) return it
        }
        return null
    }
}