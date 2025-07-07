package com.bogdanov.weatherlookupapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bogdanov.weatherlookupapp.ui.screen.city_input.CityInputScreen
import com.bogdanov.weatherlookupapp.ui.screen.forecast_detail.ForecastDetailScreen
import com.bogdanov.weatherlookupapp.ui.screen.forecast_list.ForecastListScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.CityInput.route
    ) {
        composable(Routes.CityInput.route) {
            CityInputScreen(navController)
        }

        composable(
            route = Routes.ForecastList.route,
            arguments = listOf(
                navArgument(Routes.ForecastList.ARG_CITY) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val city = backStackEntry.arguments?.getString(Routes.ForecastList.ARG_CITY) ?: return@composable
            ForecastListScreen(navController, city)
        }

        composable(
            route = Routes.ForecastDetail.route,
            arguments = listOf(
                navArgument(Routes.ForecastDetail.ARG_TIMESTAMP) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val timestamp = backStackEntry.arguments?.getLong(Routes.ForecastDetail.ARG_TIMESTAMP) ?: return@composable
            ForecastDetailScreen(timestamp)
        }
    }
}