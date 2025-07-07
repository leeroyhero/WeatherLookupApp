package com.bogdanov.weatherlookupapp.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponseDto(
    @Json(name = "list") val list: List<ForecastItemDto>
)