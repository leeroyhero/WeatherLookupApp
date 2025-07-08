package com.bogdanov.weatherlookupapp.ui.screen.forecast_list

import com.bogdanov.weatherlookupapp.domain.model.Forecast
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ForecastListViewModelTest {

    private lateinit var viewModel: ForecastListViewModel

    @Before
    fun setup() {
        viewModel = ForecastListViewModel()
    }

    @Test
    fun `setForecasts updates uiState to Ready with list`() {
        val forecast1 = mockk<Forecast>()
        val forecast2 = mockk<Forecast>()
        val list = listOf(forecast1, forecast2)

        viewModel.setForecasts(list)

        val state = viewModel.uiState.value
        assertEquals(ForecastListState.Ready(list), state)
    }
}