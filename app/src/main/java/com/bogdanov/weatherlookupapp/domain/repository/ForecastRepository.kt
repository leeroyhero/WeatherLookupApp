package com.bogdanov.weatherlookupapp.domain.repository

import com.bogdanov.weatherlookupapp.domain.model.Forecast

interface ForecastRepository {
    suspend fun getForecast(city: String): Result<List<Forecast>>
}