package com.bogdanov.weatherlookupapp.ui.screen.forecast_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bogdanov.weatherlookupapp.ui.navigation.Routes

@Composable
fun ForecastListScreen(navController: NavController, city: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Forecast for: $city", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        repeat(5) { index ->
            val fakeTimestamp = 1650000000L + index * 3600
            Text(
                text = "Forecast item $index",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Routes.ForecastDetail.createRoute(fakeTimestamp))
                    }
                    .padding(8.dp)
            )
        }
    }
}