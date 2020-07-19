package com.gmail.cities

import com.gmail.cities.domain.entity.City
import com.gmail.cities.domain.entity.Coordinates

fun getCitiesList(): MutableList<City> {
    val cityResponses: MutableList<City> = ArrayList()
    cityResponses.add(City("Alabama", "US", Coordinates(TEST_LATITUDE, TEST_LONGITUDE), TEST_COUNTRY_ID))
    cityResponses.add(City("Albuquerque", "US", Coordinates(TEST_LATITUDE, TEST_LONGITUDE), TEST_COUNTRY_ID))
    cityResponses.add(City("Anaheim", "US", Coordinates(TEST_LATITUDE, TEST_LONGITUDE), TEST_COUNTRY_ID))
    cityResponses.add(City("Arizona", "US", Coordinates(TEST_LATITUDE, TEST_LONGITUDE), TEST_COUNTRY_ID))
    cityResponses.add(City("Sydney", "AU", Coordinates(TEST_LATITUDE, TEST_LONGITUDE), TEST_COUNTRY_ID))
    return cityResponses
}