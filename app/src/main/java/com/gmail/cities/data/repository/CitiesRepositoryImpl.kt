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
    }

    override fun getAllCities(): Observable<ResultState<List<City>>> {
        return Observable.create<ResultState<List<City>>> { subscriber ->
            val listFromJson = getCitiesList().map { it.toCity() }
                    .sortedWith(compareBy(City::name, City::country))

            val result: ResultState<List<City>> = if (listFromJson.isNotEmpty()) {
                ResultState.Success(listFromJson)
            } else {
                ResultState.Loading(emptyList())
            }
            subscriber.onNext(result)
        }
                .applyComputationScheduler()
                .mapDefaultErrors()
    }

    private fun getCitiesList(): List<CityResponse> {
        return Gson().fromJson<List<CityResponse>>(
                readJSONFromAsset(context, ASSETS_CITIES_PATH),
                object : TypeToken<List<CityResponse>>() {}.type
        )
    }
}