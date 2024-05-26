package com.example.mypet.presentation.homeFragment

import com.example.mypet.domain.entity.Task

sealed class HomeState {

    data object Loading: HomeState()
    data class Success(val data: List<Task>): HomeState()
    data class Error(val msg: String): HomeState()
}