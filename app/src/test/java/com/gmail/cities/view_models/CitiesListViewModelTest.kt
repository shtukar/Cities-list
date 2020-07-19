package com.gmail.cities.view_models

import com.gmail.cities.BaseTest
import com.gmail.cities.TEST_CODE_ERROR
import com.gmail.cities.TEST_FILTER_REQUEST
import com.gmail.cities.TEST_MESSAGE_ERROR
import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import com.gmail.cities.domain.entity.ErrorState
import com.gmail.cities.domain.usecase.CitiesUseCase
import com.gmail.cities.presentation.ui.cities_list.CitiesListViewModel
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class CitiesListViewModelTest : BaseTest() {

    @Mock
    lateinit var useCase: CitiesUseCase

    @InjectMocks
    lateinit var viewModel: CitiesListViewModel

    @Test
    fun `get all cities`() {
        val resultSuccess: ResultState<List<City>> = ResultState.Success(listOf())
        Mockito.`when`(useCase.getAllCities(null)).thenReturn(Observable.just(resultSuccess))
        viewModel.getAllCities(null)
        Assert.assertEquals(resultSuccess, viewModel.getAllCitiesStatus().value)
    }

    @Test
    fun `get all cities error`() {
        val resultError: ResultState<List<City>> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), emptyList())
        Mockito.`when`(useCase.getAllCities(null)).thenReturn(Observable.just(resultError))
        viewModel.getAllCities(null)
        Assert.assertEquals(resultError, viewModel.getAllCitiesStatus().value)
    }

    @Test
    fun `get all cities with filter`() {
        val resultSuccess: ResultState<List<City>> = ResultState.Success(listOf())
        Mockito.`when`(useCase.getAllCities(TEST_FILTER_REQUEST)).thenReturn(Observable.just(resultSuccess))
        viewModel.getAllCities(TEST_FILTER_REQUEST)
        Assert.assertEquals(resultSuccess, viewModel.getAllCitiesStatus().value)
    }

    @Test
    fun `get all cities with filter error`() {
        val resultError: ResultState<List<City>> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), emptyList())
        Mockito.`when`(useCase.getAllCities(TEST_FILTER_REQUEST)).thenReturn(Observable.just(resultError))
        viewModel.getAllCities(TEST_FILTER_REQUEST)
        Assert.assertEquals(resultError, viewModel.getAllCitiesStatus().value)
    }
}