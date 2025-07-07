package com.bogdanov.weatherlookupapp.data.repostiory

import com.bogdanov.weatherlookupapp.data.mapper.ForecastMapper
import com.bogdanov.weatherlookupapp.data.network.service.WeatherApiService
import com.bogdanov.weatherlookupapp.domain.model.Forecast
import com.bogdanov.weatherlookupapp.domain.repository.ForecastRepository
import javax.inject.Inject

private const val OPEN_WEATHER_API_KEY = "65d00499677e59496ca2f318eb68c049"

class ForecastRepositoryImpl
@Inject constructor(
    private val api: WeatherApiService,
    private val mapper: ForecastMapper
) : ForecastRepository {

    override suspend fun getForecast(city: String): Result<List<Forecast>> {
        return try {
            val response = api.getForecast(
                city = city,
                apiKey = OPEN_WEATHER_API_KEY
            )
            val result = mapper.mapList(response.list)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}