package com.gmail.cities.repository

import android.content.Context
import com.gmail.cities.BaseTest
import com.gmail.cities.data.repository.CitiesRepositoryImpl
import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import com.gmail.cities.getCitiesList
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.internal.util.reflection.FieldSetter

class CitiesRepositoryTest : BaseTest() {

    override fun setup() {
        super.setup()
        repository = CitiesRepositoryImpl(context)
        setSortedCitiesList(getCitiesList(), repository)
    }

    @Mock
    lateinit var context: Context

    lateinit var repository: CitiesRepositoryImpl

    @Test
    fun `get all cities without filter`() {
        val result = (repository.getAllCities(null).blockingFirst() as? ResultState.Success)?.data
        Assert.assertEquals(5, result?.size)
    }

    @Test
    fun `get all cities with filter A`() {
        val filterRequest = "A"
        val result = (repository.getAllCities(filterRequest).blockingFirst() as? ResultState.Success)?.data
        Assert.assertEquals(4, result?.size)
        result?.forEach { city ->
            Assert.assertTrue(city.name.startsWith(filterRequest))
        }
    }

    @Test
    fun `get all cities with filter Al`() {
        val filterRequest = "Al"
        val result = (repository.getAllCities(filterRequest).blockingFirst() as? ResultState.Success)?.data
        Assert.assertEquals(2, result?.size)
        result?.forEach { city ->
            Assert.assertTrue(city.name.startsWith(filterRequest))
        }
    }

    @Test
    fun `get all cities with filter Alb`() {
        val filterRequest = "Alb"
        val result = (repository.getAllCities(filterRequest).blockingFirst() as? ResultState.Success)?.data
        Assert.assertEquals(1, result?.size)
        result?.forEach { city ->
            Assert.assertTrue(city.name.startsWith(filterRequest))
        }
    }

    @Test
    fun `get all cities with filter ALB case insensitive`() {
        val filterRequest = "ALB"
        val result = (repository.getAllCities(filterRequest).blockingFirst() as? ResultState.Success)?.data
        Assert.assertEquals(1, result?.size)
        result?.forEach { city ->
            Assert.assertTrue(city.name.startsWith(filterRequest, ignoreCase = true))
        }
    }

    @Test
    fun `get all cities with incorrect filter`() {
        val result = (repository.getAllCities("Test").blockingFirst() as? ResultState.Loading)?.data
        Assert.assertEquals(0, result?.size)
    }

    private fun setSortedCitiesList(validationIdString: List<City>, repository: Any) {
        FieldSetter.setField(repository, repository.javaClass.getDeclaredField("sortedCitiesList"),
                validationIdString)
    }
}