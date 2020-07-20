package com.gmail.cities.presentation.ui.cities_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.cities.data.common.applyIoScheduler
import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.CacheInfo
import com.gmail.cities.domain.entity.City
import com.gmail.cities.domain.usecase.CitiesUseCase
import com.gmail.cities.presentation.common.BaseViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
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
    fun getAllCities() {
        citiesUseCase.getAllCities().subscribe { resultState: ResultState<List<City>> ->
            allCitiesStatus.postValue(resultState)
        }.track()
    }

    fun getFilterResultRange(list: List<City>, filter: String): Observable<CacheInfo> {
        return Observable.create(ObservableOnSubscribe<CacheInfo> { subscriber ->
            val binaryIndex = list.binarySearch {
                when {
                    it.name.startsWith(filter, ignoreCase = true) -> 0
                    it.name.compareTo(filter, ignoreCase = true) > 0 -> 1
                    else -> -1
                }
            }
            if (binaryIndex < 0) {
                subscriber.onNext(CacheInfo(filter, -1, -1))
            } else {
                var currentFrom = binaryIndex
                while (currentFrom > 0 &&
                        list[currentFrom - 1].name.startsWith(filter, ignoreCase = true)) {
                    currentFrom--
                }
                var currentTo = binaryIndex
                while (currentTo < list.size - 1 &&
                        list[currentTo + 1].name.startsWith(filter, ignoreCase = true)) {
                    currentTo++
                }
                subscriber.onNext(CacheInfo(filter, currentFrom, currentTo))
            }

        }).applyIoScheduler()
    }
}
