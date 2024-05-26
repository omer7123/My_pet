package com.example.mypet.domain.entity

data class Task(
    val id: String,
    val owner_id: String,
    val pet_id: String,
    val title: String,
    val description: String,
    val date: Long
)

data class NewTask(
    val pet_id: String,
    val title: String,
    val description: String,
    val date: Long,
    val owner: Owner
)