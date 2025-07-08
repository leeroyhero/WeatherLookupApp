package com.bogdanov.weatherlookupapp.ui.screen.forecast_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Forecast", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))

                state.list.forEach { forecast ->
                    Text(
                        text = forecast.dateTime,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                sharedForecastViewModel.selectForecast(forecast)
                                navController.navigate(Routes.ForecastDetail.route)
                            }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}