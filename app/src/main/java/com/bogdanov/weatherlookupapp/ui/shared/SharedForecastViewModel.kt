package com.bogdanov.weatherlookupapp.ui.shared

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.bogdanov.weatherlookupapp.domain.model.Forecast

@HiltViewModel
class SharedForecastViewModel @Inject constructor() : ViewModel() {

    private val _forecastList = MutableStateFlow<List<Forecast>>(emptyList())
    val forecastList = _forecastList.asStateFlow()

    private val _selectedForecast = MutableStateFlow<Forecast?>(null)
    val selectedForecast = _selectedForecast.asStateFlow()

    fun setForecasts(list: List<Forecast>) {
        _forecastList.value = list
    }

    fun selectForecast(item: Forecast) {
        _selectedForecast.value = item
    }
}