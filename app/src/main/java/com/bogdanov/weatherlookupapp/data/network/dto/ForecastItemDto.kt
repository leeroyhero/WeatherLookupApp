package com.bogdanov.weatherlookupapp.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastItemDto(
    @Json(name = "dt") val timestamp: Long,
    @Json(name = "dt_txt") val dateText: String,
    @Json(name = "main") val main: MainInfoDto,
    @Json(name = "weather") val weather: List<WeatherInfoDto>
)