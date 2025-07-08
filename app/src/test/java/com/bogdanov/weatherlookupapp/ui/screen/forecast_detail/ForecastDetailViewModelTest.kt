package com.bogdanov.weatherlookupapp.ui.screen.forecast_detail

import com.bogdanov.weatherlookupapp.domain.model.Forecast
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ForecastDetailViewModelTest {

    private lateinit var viewModel: ForecastDetailViewModel

    @Before
    fun setup() {
        viewModel = ForecastDetailViewModel()
    }

    @Test
    fun `setForecast updates uiState to Ready`() {
        val mockForecast = mockk<Forecast>()
        viewModel.setForecast(mockForecast)

        val state = viewModel.uiState.value
        assertEquals(ForecastDetailState.Ready(mockForecast), state)
    }
}