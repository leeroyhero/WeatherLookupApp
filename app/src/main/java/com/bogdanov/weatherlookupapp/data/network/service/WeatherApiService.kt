package com.bogdanov.weatherlookupapp.data.network.service

import com.bogdanov.weatherlookupapp.data.network.dto.ForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): ForecastResponseDto
}