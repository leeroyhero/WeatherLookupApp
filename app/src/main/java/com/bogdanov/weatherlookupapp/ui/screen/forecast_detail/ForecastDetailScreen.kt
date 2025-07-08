package com.bogdanov.weatherlookupapp.ui.screen.forecast_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bogdanov.weatherlookupapp.domain.model.Forecast
import com.bogdanov.weatherlookupapp.ui.components.CityTopBar
import com.bogdanov.weatherlookupapp.ui.shared.SharedForecastViewModel

@Composable
fun ForecastDetailScreen(
    navController: NavController,
    sharedForecastViewModel: SharedForecastViewModel = hiltViewModel(),
    viewModel: ForecastDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        sharedForecastViewModel.selectedForecast?.let { viewModel.setForecast(it) }
    }

    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            CityTopBar(
                cityName = sharedForecastViewModel.cityName,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is ForecastDetailState.Ready -> {
                ForecastDetailContent(
                    forecast = uiState.forecast,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            is ForecastDetailState.Loading -> {
            }
        }
    }
}

@Composable
private fun ForecastDetailContent(
    forecast: Forecast,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "${forecast.temperature}°F",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Date: ${forecast.dateTime}")
        Text("Feels Like: ${forecast.feelsLike}°F")
        Text("Weather: ${forecast.weatherMain} (${forecast.weatherDescription})")
        Text("Humidity: ${forecast.humidity}%")
        Text("Pressure: ${forecast.pressure} hPa")
    }
}