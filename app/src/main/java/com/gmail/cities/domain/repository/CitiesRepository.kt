package com.gmail.cities.domain.repository

import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import io.reactivex.Observable

interface CitiesRepository {
    fun getAllCities(): Observable<ResultState<List<City>>>
}
