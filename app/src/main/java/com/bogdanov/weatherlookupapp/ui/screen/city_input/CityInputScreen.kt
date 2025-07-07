package com.bogdanov.weatherlookupapp.ui.screen.city_input

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bogdanov.weatherlookupapp.ui.navigation.Routes
import com.bogdanov.weatherlookupapp.ui.shared.SharedForecastViewModel

@Composable
fun CityInputScreen(
    navController: NavController,
    viewModel: CityInputViewModel = hiltViewModel(),
    sharedForecastViewModel: SharedForecastViewModel = hiltViewModel()
) {
    var city by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        val state = uiState
        if (state is CityInputState.Success) {
            sharedForecastViewModel.setForecasts(state.list)
            navController.navigate(Routes.ForecastList.route)
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.fetchForecast(city) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("See Forecast")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is CityInputState.Loading -> CircularProgressIndicator()
            is CityInputState.Error -> Text(
                text = (uiState as CityInputState.Error).message,
                color = MaterialTheme.colorScheme.error
            )
            else -> {}
        }
    }
}