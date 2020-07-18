package com.gmail.cities.domain.entity

data class City(
    val name: String,
    val country: String,
    val coordinates: Coordinates,
    val id: Int
)