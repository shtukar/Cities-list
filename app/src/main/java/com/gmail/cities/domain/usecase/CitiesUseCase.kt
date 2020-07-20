package com.gmail.cities.domain.usecase

import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import io.reactivex.Observable

interface CitiesUseCase {
    fun getAllCities(): Observable<ResultState<List<City>>>
}
