package com.example.mypet.presentation.authFragment

import com.example.mypet.domain.entity.User

sealed class AuthState {

    data object Loading: AuthState()

    data class Success(val data: User): AuthState()

    data class Error(val msg: String): AuthState()
}