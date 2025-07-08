package com.bogdanov.weatherlookupapp.ui.screen.forecast_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bogdanov.weatherlookupapp.domain.model.Forecast
import com.bogdanov.weatherlookupapp.ui.navigation.Routes
import com.bogdanov.weatherlookupapp.ui.shared.SharedForecastViewModel

@Composable
fun ForecastListScreen(
    navController: NavController,
    sharedForecastViewModel: SharedForecastViewModel = hiltViewModel(),
    viewModel: ForecastListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setForecasts(sharedForecastViewModel.forecastList)
    }

    when (val state = uiState) {
        is ForecastListState.Ready -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Forecast", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.list) { forecast ->
                        ForecastListItem(
                            forecast = forecast,
                            onClick = {
                                sharedForecastViewModel.selectForecast(forecast)
                                navController.navigate(Routes.ForecastDetail.route)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ForecastListItem(
    forecast: Forecast,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Text(text = forecast.dateTime, style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "${forecast.weatherMain}, ${forecast.temperature}°F",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}