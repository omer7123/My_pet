package com.example.mypet.presentation.detailTaskFragment

import com.example.mypet.domain.entity.Task

sealed class DetailTaskState {

    data object Loading: DetailTaskState()
    data class Success(val task: Task): DetailTaskState()
    data class Error(val msg: String): DetailTaskState()
}