package com.example.mypet.presentation.detailPetFragment

import com.example.mypet.domain.entity.PetItem

sealed class DetailPetState {

    data object Loading : DetailPetState()
    data class Content(val animal: String, val breed: String, val data: PetItem) : DetailPetState()
    data class Error(val msg: String) : DetailPetState()
}