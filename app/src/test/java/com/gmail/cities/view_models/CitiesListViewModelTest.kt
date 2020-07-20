package com.gmail.cities.view_models

import com.gmail.cities.*
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
        Mockito.`when`(useCase.getAllCities()).thenReturn(Observable.just(resultSuccess))
        viewModel.getAllCities()
        Assert.assertEquals(resultSuccess, viewModel.getAllCitiesStatus().value)
    }

    @Test
    fun `get all cities error`() {
        val resultError: ResultState<List<City>> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), emptyList())
        Mockito.`when`(useCase.getAllCities()).thenReturn(Observable.just(resultError))
        viewModel.getAllCities()
        Assert.assertEquals(resultError, viewModel.getAllCitiesStatus().value)
    }

    @Test
    fun `get filter range for A`() {
        val filterRequest = "A"
        val result = viewModel.getFilterResultRange(getCitiesList(), filterRequest).blockingFirst()
        val listSize = result?.rangeTo?.minus(result.rangeFrom)?.plus(1)
        Assert.assertEquals(4, listSize)
    }

    @Test
    fun `get filter range for Al`() {
        val filterRequest = "Al"
        val result = viewModel.getFilterResultRange(getCitiesList(), filterRequest).blockingFirst()
        val listSize = result?.rangeTo?.minus(result.rangeFrom)?.plus(1)
        Assert.assertEquals(2, listSize)
    }

    @Test
    fun `get filter range for Alb`() {
        val filterRequest = "Alb"
        val result = viewModel.getFilterResultRange(getCitiesList(), filterRequest).blockingFirst()
        val listSize = result?.rangeTo?.minus(result.rangeFrom)?.plus(1)
        Assert.assertEquals(1, listSize)
    }

    @Test
    fun `get filter range for ALB case insensitive`() {
        val filterRequest = "ALB"
        val result = viewModel.getFilterResultRange(getCitiesList(), filterRequest).blockingFirst()
        val listSize = result?.rangeTo?.minus(result.rangeFrom)?.plus(1)
        Assert.assertEquals(1, listSize)
    }

    @Test
    fun `get filter range for incorrect filter`() {
        val filterRequest = "Test"
        val result = viewModel.getFilterResultRange(getCitiesList(), filterRequest).blockingFirst()
        val listSize = result?.rangeTo?.minus(result.rangeFrom)
        Assert.assertEquals(0, listSize)
    }
}