package com.gmail.cities.data.entity

import com.gmail.cities.domain.entity.City
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("coord") val coordinates: CoordinatesResponse,
    @SerializedName("_id") val id: Int
) {

    fun toCity(): City {
        return City(name, country, coordinates.toCoordinates(), id)
    }
}