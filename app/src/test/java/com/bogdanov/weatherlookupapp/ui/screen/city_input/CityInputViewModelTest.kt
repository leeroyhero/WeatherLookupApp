package com.bogdanov.weatherlookupapp.ui.screen.city_input

import com.bogdanov.weatherlookupapp.domain.model.Forecast
import com.bogdanov.weatherlookupapp.domain.repository.ForecastRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CityInputViewModelTest {

    private lateinit var forecastRepository: ForecastRepository
    private lateinit var viewModel: CityInputViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        forecastRepository = mockk()
        viewModel = CityInputViewModel(forecastRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchForecast with blank input emits Error`() = runTest {
        viewModel.fetchForecast("   ")
        assertEquals(CityInputState.Error("Please enter a city name."), viewModel.uiState.value)
    }

    @Test
    fun `fetchForecast success emits Loading then Success`() = runTest {
        val mockForecasts = listOf(mockk<Forecast>(), mockk<Forecast>())
        coEvery { forecastRepository.getForecast("London") } returns Result.success(mockForecasts)

        viewModel.fetchForecast("London")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(CityInputState.Success(mockForecasts), viewModel.uiState.value)
    }

    @Test
    fun `fetchForecast failure emits Loading then Error`() = runTest {
        coEvery { forecastRepository.getForecast("Paris") } returns Result.failure(Exception("Network error"))

        viewModel.fetchForecast("Paris")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(CityInputState.Error("Failed to load forecast for Paris."), viewModel.uiState.value)
    }

    @Test
    fun `resetState sets state to Idle`() = runTest {
        viewModel.resetState()
        assertEquals(CityInputState.Idle, viewModel.uiState.value)
    }
}