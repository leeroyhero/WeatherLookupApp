package com.bogdanov.weatherlookupapp.ui.screen.city_input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bogdanov.weatherlookupapp.domain.repository.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CityInputState>(CityInputState.Idle)
    val uiState: StateFlow<CityInputState> = _uiState

    fun fetchForecast(input: String) {
        val city = input.trim()
        if (city.isBlank()) {
            _uiState.value = CityInputState.Error("Please enter a city name.")
            return
        }

        _uiState.value = CityInputState.Loading

        viewModelScope.launch {
            forecastRepository.getForecast(city)
                .onSuccess { forecasts ->
                    _uiState.value = CityInputState.Success(forecasts)
                }
                .onFailure { error->
                    _uiState.value = CityInputState.Error("Failed to load forecast for $city.")
                }
        }
    }

    fun resetState() {
        _uiState.value = CityInputState.Idle
    }
}