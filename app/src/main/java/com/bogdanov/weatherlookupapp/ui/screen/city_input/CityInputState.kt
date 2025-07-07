package com.bogdanov.weatherlookupapp.ui.screen.city_input

import com.bogdanov.weatherlookupapp.domain.model.Forecast

sealed class CityInputState {
    object Idle : CityInputState()
    object Loading : CityInputState()
    data class Success(val list: List<Forecast>) : CityInputState()
    data class Error(val message: String) : CityInputState()
}