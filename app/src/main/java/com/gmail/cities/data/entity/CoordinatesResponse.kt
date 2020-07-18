package com.gmail.cities.data.entity

import com.gmail.cities.domain.entity.Coordinates
import com.google.gson.annotations.SerializedName

data class CoordinatesResponse(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
) {

    fun toCoordinates(): Coordinates {
        return Coordinates(lat, lon)
    }
}