package com.example.mypet.domain.entity

data class Animal(
    val id:String,
    val name: String

) {
    override fun toString(): String {
        return name
    }
}

data class Breed(
    val id: String,
    val name: String,
    val animal_id: String,

){
    override fun toString(): String {
        return name
    }
}

data class NewPet(
    val name:String,
    val age: Int,
    val gender: String,
    val owner: Owner
)
data class Owner(
    var token: String
)

data class PetItem(
    val about: String,
    val age: Int,
    val animal_id: String,
    val birthday: Int,
    val breed_id: String,
    val gender: String,
    val height: Int,
    val id: String,
    val name: String,
    val owner_id: String,
    val weight: Int
)
