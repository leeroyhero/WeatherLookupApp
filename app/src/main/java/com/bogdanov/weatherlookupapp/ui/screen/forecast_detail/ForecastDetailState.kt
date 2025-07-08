package com.bogdanov.weatherlookupapp.ui.screen.forecast_detail

import com.bogdanov.weatherlookupapp.domain.model.Forecast

sealed class ForecastDetailState {
    data object Loading : ForecastDetailState()
    data class Ready(val forecast: Forecast) : ForecastDetailState()
}