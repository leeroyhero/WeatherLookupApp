package com.bogdanov.weatherlookupapp.ui.screen.forecast_list

import com.bogdanov.weatherlookupapp.domain.model.Forecast

sealed class ForecastListState {
    data class Ready(val list: List<Forecast>) : ForecastListState()
}