package com.bogdanov.weatherlookupapp.data.repostiory

import com.bogdanov.weatherlookupapp.data.network.dto.ForecastItemDto
import com.bogdanov.weatherlookupapp.data.network.dto.ForecastResponseDto
import com.bogdanov.weatherlookupapp.data.network.service.WeatherApiService
import com.bogdanov.weatherlookupapp.domain.mapper.ForecastMapper
import com.bogdanov.weatherlookupapp.domain.model.Forecast
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ForecastRepositoryImplTest {

    private lateinit var api: WeatherApiService
    private lateinit var mapper: ForecastMapper
    private lateinit var repository: ForecastRepositoryImpl

    @Before
    fun setup() {
        api = mockk()
        mapper = mockk()
        repository = ForecastRepositoryImpl(api, mapper)
    }

    @Test
    fun `getForecast returns success with mapped data`() = runTest {
        // Given
        val city = "London"
        val dtoList = mockk<List<ForecastItemDto>>()

        val expectedForecast = listOf(mockk<Forecast>())

        coEvery { api.getForecast(city = city, apiKey = any()) } returns ForecastResponseDto(list = dtoList)
        every { mapper.mapList(dtoList) } returns expectedForecast

        // When
        val result = repository.getForecast(city)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedForecast, result.getOrNull())
    }

    @Test
    fun `getForecast returns failure on exception`() = runTest {
        // Given
        val city = "Paris"
        coEvery { api.getForecast(city = city, apiKey = any()) } throws IOException("Network error")

        // When
        val result = repository.getForecast(city)

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IOException)
    }
}