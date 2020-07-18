package com.gmail.cities.data.repository

import android.content.Context
import com.gmail.cities.data.common.applyIoScheduler
import com.gmail.cities.data.common.mapDefaultErrors
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

    override fun getAllCities(filter: String?): Observable<ResultState<List<City>>> {
        return Observable.just(getCities())
            .applyIoScheduler()
            .map {
                if (it.isNotEmpty()) {
                    ResultState.Success(it.map { it.toCity() })
                } else {
                    ResultState.Loading(emptyList<City>())
                }
            }
            .mapDefaultErrors()
    }

    private fun getCities(): List<CityResponse> {
        return Gson().fromJson<List<CityResponse>>(
            readJSONFromAsset(),
            object : TypeToken<List<CityResponse>>() {}.type
        )
    }

    private fun readJSONFromAsset(): String? {
        return context.assets.open(ASSETS_CITIES_PATH).bufferedReader().use {
            it.readText()
        }
    }
}