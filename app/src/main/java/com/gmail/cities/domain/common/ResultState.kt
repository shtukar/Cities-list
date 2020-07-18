package com.gmail.cities.domain.common

import com.gmail.cities.domain.entity.ErrorState

sealed class ResultState<T> {

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<T>(val data: T) : ResultState<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ResultState<T>()

    /**
     * A state to show a [error] is thrown.
     */
    data class Error<T>(val error: ErrorState, val data: T?) : ResultState<T>()
}
