package com.example.mypet.presentation.createTaskFragment

import com.example.mypet.domain.entity.PetItem
import com.example.mypet.domain.entity.Task

sealed class CreateTaskState {

    data object Loading : CreateTaskState()

    data class Success(val data: Task) : CreateTaskState()
    data class SuccessPets(val data: List<PetItem>) : CreateTaskState()

    data class Error(val msg: String) : CreateTaskState()
}