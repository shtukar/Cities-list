package com.gmail.cities.data.repository

import android.content.Context
import com.gmail.cities.data.common.applyComputationScheduler
import com.gmail.cities.data.entity.CityResponse
import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import com.gmail.cities.domain.repository.CitiesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable

class CitiesRepositoryImpl(private val context: Context) : CitiesRepository {

    companion object {
        const val ASSETS_CITIES_PATH = "cities.json"
    }

    lateinit var sortedCitiesList: List<City>

    override fun getAllCities(filter: String?): Observable<ResultState<List<City>>> {
        return Observable.create<ResultState<List<City>>> { subscriber ->
            sortedCitiesList = getCities().map { it.toCity() }
                    .sortedWith(compareBy(City::name, City::country))

            val result: ResultState<List<City>> = if (sortedCitiesList.isNotEmpty()) {
                ResultState.Success(sortedCitiesList)
            } else {
                ResultState.Loading(emptyList())
            }
            subscriber.onNext(result)
        }
                .applyComputationScheduler()
    }

    private fun getCities(): List<CityResponse> {
        return Gson().fromJson<List<CityResponse>>(readJSONFromAsset(),
                object : TypeToken<List<CityResponse>>() {}.type)
    }

    private fun readJSONFromAsset(): String? {
        return context.assets.open(ASSETS_CITIES_PATH)
                .bufferedReader()
                .use { it.readText() }
    }
}