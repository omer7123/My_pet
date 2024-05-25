package com.example.mypet.presentation.petsFragment

import com.example.mypet.domain.entity.PetItem

sealed class PetsState {

    data object Loading: PetsState()
    data class Success(val data: List<PetItem>): PetsState()
    data class Error(val msg: String): PetsState()
}
