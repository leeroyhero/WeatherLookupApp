package com.bogdanov.weatherlookupapp.ui.navigation

sealed class Routes(val route: String) {
    object CityInput : Routes("city_input")

    object ForecastList : Routes("forecast_list/{city}") {
        const val ARG_CITY = "city"
        fun createRoute(city: String) = "forecast_list/$city"
    }

    object ForecastDetail : Routes("forecast_detail/{timestamp}") {
        const val ARG_TIMESTAMP = "timestamp"
        fun createRoute(timestamp: Long) = "forecast_detail/$timestamp"
    }
}