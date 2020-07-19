package com.gmail.cities.data.repository

import android.content.Context
import com.gmail.cities.data.common.applyComputationScheduler
import com.gmail.cities.data.common.mapDefaultErrors
import com.gmail.cities.data.entity.CityResponse
import com.gmail.cities.data.readJSONFromAsset
import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import com.gmail.cities.domain.repository.CitiesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable

class CitiesRepositoryImpl(private val context: Context) : CitiesRepository {

    companion object {
        const val ASSETS_CITIES_PATH = "cities.json"
        const val EMPTY_STRING = ""
    }

    private var sortedCitiesList = listOf<City>()
    private var filterList = listOf<City>()
    private var previousFilterRequest: String = EMPTY_STRING

    override fun getAllCities(filter: String?): Observable<ResultState<List<City>>> {
        return Observable.create<ResultState<List<City>>> { subscriber ->
            if (sortedCitiesList.isNullOrEmpty()) {
                sortedCitiesList = getCitiesList().map { it.toCity() }
                        .sortedWith(compareBy(City::name, City::country))
            }

            filterList = if (filter.isNullOrEmpty()) {
                sortedCitiesList.toMutableList()
            } else {
                if (previousFilterRequest.isNotEmpty() && filter != previousFilterRequest
                        && filter.startsWith(previousFilterRequest)) {
                    filterList.filter { it.name.startsWith(filter, ignoreCase = true) }
                } else {
                    sortedCitiesList.filter { it.name.startsWith(filter, ignoreCase = true) }
                }
            }

            val result: ResultState<List<City>> = if (filterList.isNotEmpty()) {
                previousFilterRequest = filter ?: EMPTY_STRING
                ResultState.Success(filterList)
            } else {
                ResultState.Loading(emptyList())
            }
            subscriber.onNext(result)
        }
                .distinct()
                .applyComputationScheduler()
                .mapDefaultErrors()
    }

    private fun getCitiesList(): List<CityResponse> {
        return Gson().fromJson<List<CityResponse>>(readJSONFromAsset(context, ASSETS_CITIES_PATH),
                object : TypeToken<List<CityResponse>>() {}.type)
    }
}