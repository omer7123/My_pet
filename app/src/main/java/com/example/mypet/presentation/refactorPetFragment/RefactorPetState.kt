package com.example.mypet.presentation.refactorPetFragment

import com.example.mypet.domain.entity.Animal
import com.example.mypet.domain.entity.Breed

sealed class RefactorPetState {

    data object Loading: RefactorPetState()

    data class SuccessAnimal(val animals: List<Animal>): RefactorPetState()
    data class SuccessBreed(val breeds: List<Breed>): RefactorPetState()

    data class Error(val msg: String): RefactorPetState()
}