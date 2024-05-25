package com.example.mypet.presentation.createPetFragment

import com.example.mypet.domain.entity.PetItem

sealed class CreatePetState {

    data object Loading: CreatePetState()

    data class Success(val data: PetItem): CreatePetState()

    data class Error(val msg: String): CreatePetState()
}