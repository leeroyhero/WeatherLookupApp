package com.bogdanov.weatherlookupapp.ui.navigation

sealed class Routes(val route: String) {
    object CityInput : Routes("city_input")

    object ForecastList : Routes("forecast_list")

    object ForecastDetail : Routes("forecast_detail")
}