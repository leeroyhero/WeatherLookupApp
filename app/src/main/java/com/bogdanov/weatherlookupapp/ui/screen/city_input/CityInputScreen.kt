package com.bogdanov.weatherlookupapp.ui.screen.city_input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bogdanov.weatherlookupapp.R
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
            sharedForecastViewModel.setCityName(city)
            navController.navigate(Routes.ForecastList.route)
            viewModel.resetState()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text(stringResource(R.string.enter_city_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.fetchForecast(city) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(R.string.see_forecast))
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (uiState is CityInputState.Error) {
                Text(
                    text = (uiState as CityInputState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        if (uiState is CityInputState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}