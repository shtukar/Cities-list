package com.gmail.cities.domain.usecase

import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import com.gmail.cities.domain.repository.CitiesRepository
import io.reactivex.Observable

class CitiesUseCaseImpl(private val repository: CitiesRepository) : CitiesUseCase {

    override fun getAllCities(filter: String?): Observable<ResultState<List<City>>> =
        repository.getAllCities(filter)
}
