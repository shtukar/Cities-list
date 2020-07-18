package com.gmail.cities.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
    val lat: Double,
    val lon: Double
) : Parcelable