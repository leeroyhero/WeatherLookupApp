package com.bogdanov.weatherlookupapp.ui.shared

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.bogdanov.weatherlookupapp.domain.model.Forecast

@HiltViewModel
class SharedForecastViewModel @Inject constructor() : ViewModel() {

    var forecastList: List<Forecast> = emptyList()
        private set

    var selectedForecast: Forecast? = null
        private set

    var cityName: String = ""
        private set

    fun setCityName(name: String) {
        cityName = name
    }

    fun setForecasts(list: List<Forecast>) {
        forecastList = list
    }

    fun selectForecast(item: Forecast) {
        selectedForecast = item
    }
}