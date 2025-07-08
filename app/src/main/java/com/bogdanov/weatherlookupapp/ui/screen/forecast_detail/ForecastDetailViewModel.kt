package com.bogdanov.weatherlookupapp.ui.screen.forecast_detail

import androidx.lifecycle.ViewModel
import com.bogdanov.weatherlookupapp.domain.model.Forecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ForecastDetailViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<ForecastDetailState>(ForecastDetailState.Loading)
    val uiState: StateFlow<ForecastDetailState> = _uiState

    fun setForecast(forecast: Forecast) {
        _uiState.value = ForecastDetailState.Ready(forecast)
    }
}