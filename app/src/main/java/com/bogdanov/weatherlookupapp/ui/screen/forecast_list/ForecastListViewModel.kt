package com.bogdanov.weatherlookupapp.ui.screen.forecast_list

import androidx.lifecycle.ViewModel
import com.bogdanov.weatherlookupapp.domain.model.Forecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ForecastListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<ForecastListState>(ForecastListState.Ready(emptyList()))
    val uiState: StateFlow<ForecastListState> = _uiState

    fun setForecasts(list: List<Forecast>) {
        _uiState.value = ForecastListState.Ready(list)
    }
}