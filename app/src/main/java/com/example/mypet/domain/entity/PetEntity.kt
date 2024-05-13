package com.example.mypet.domain.entity

import java.io.Serializable

data class PetEntity(
    val id: String,
    val name: String,
    val breed: String?,
    val yo: Int?,
    val weight: Int?,
    val height: Int?
) :Serializable
