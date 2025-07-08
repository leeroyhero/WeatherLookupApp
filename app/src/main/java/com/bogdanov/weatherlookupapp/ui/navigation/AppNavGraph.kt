package com.bogdanov.weatherlookupapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.bogdanov.weatherlookupapp.ui.screen.city_input.CityInputScreen
import com.bogdanov.weatherlookupapp.ui.screen.forecast_detail.ForecastDetailScreen
import com.bogdanov.weatherlookupapp.ui.screen.forecast_list.ForecastListScreen
import com.bogdanov.weatherlookupapp.ui.shared.SharedForecastViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_graph"
    ) {
        navigation(
            route = "main_graph",
            startDestination = Routes.CityInput.route
        ) {
            composable(Routes.CityInput.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("main_graph")
                }
                val sharedViewModel = hiltViewModel<SharedForecastViewModel>(parentEntry)
                CityInputScreen(navController, sharedForecastViewModel = sharedViewModel)
            }

            composable(Routes.ForecastList.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("main_graph")
                }
                val sharedViewModel = hiltViewModel<SharedForecastViewModel>(parentEntry)
                ForecastListScreen(navController, sharedForecastViewModel = sharedViewModel)
            }

            composable(Routes.ForecastDetail.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("main_graph")
                }
                val sharedViewModel = hiltViewModel<SharedForecastViewModel>(parentEntry)
                ForecastDetailScreen(sharedForecastViewModel = sharedViewModel)
            }
        }
    }
}