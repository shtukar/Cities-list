package com.gmail.cities.presentation.ui.cities_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import com.gmail.cities.domain.usecase.CitiesUseCase
import com.gmail.cities.presentation.common.BaseViewModel
import javax.inject.Inject

/**
 * CitiesListViewModel retrieves the information about the Cities from the storage and
 * returns it formatted to the view.
 */
class CitiesListViewModel @Inject constructor(private val citiesUseCase: CitiesUseCase) : BaseViewModel() {

    private val allCitiesStatus by lazy { MutableLiveData<ResultState<List<City>>>() }

    fun getAllCitiesStatus(): LiveData<ResultState<List<City>>> = allCitiesStatus

    /**
     * Get the whole information about cities.
     */
    fun getAllCities(filter: String?) {
        onCleared()
        citiesUseCase.getAllCities(filter).subscribe { resultState: ResultState<List<City>> ->
            allCitiesStatus.postValue(resultState)
        }.track()
    }
}
