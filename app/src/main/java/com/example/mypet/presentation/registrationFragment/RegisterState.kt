package com.example.mypet.presentation.registrationFragment

import com.example.mypet.domain.entity.User

sealed class RegisterState {

    data object Loading: RegisterState()

    data class Success(val data: User): RegisterState()

    data class Error(val msg: String): RegisterState()
}