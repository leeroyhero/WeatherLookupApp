package com.bogdanov.weatherlookupapp.ui.shared

import com.bogdanov.weatherlookupapp.domain.model.Forecast
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SharedForecastViewModelTest {

    private lateinit var viewModel: SharedForecastViewModel

    @Before
    fun setup() {
        viewModel = SharedForecastViewModel()
    }

    @Test
    fun `setCityName updates cityName`() {
        viewModel.setCityName("New York")
        assertEquals("New York", viewModel.cityName)
    }

    @Test
    fun `setForecasts updates forecastList`() {
        val forecast1 = mockk<Forecast>(relaxed = true)
        val forecast2 = mockk<Forecast>(relaxed = true)
        val forecasts = listOf(forecast1, forecast2)

        viewModel.setForecasts(forecasts)

        assertEquals(forecasts, viewModel.forecastList)
    }

    @Test
    fun `selectForecast updates selectedForecast`() {
        val forecast = mockk<Forecast>(relaxed = true)

        viewModel.selectForecast(forecast)

        assertEquals(forecast, viewModel.selectedForecast)
    }
}