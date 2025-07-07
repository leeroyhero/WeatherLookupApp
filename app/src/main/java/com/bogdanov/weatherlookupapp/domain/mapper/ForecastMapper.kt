package com.bogdanov.weatherlookupapp.domain.mapper

import com.bogdanov.weatherlookupapp.data.network.dto.ForecastItemDto
import com.bogdanov.weatherlookupapp.domain.model.Forecast
import javax.inject.Inject

class ForecastMapper
@Inject constructor() {

    fun mapList(dtoList: List<ForecastItemDto>): List<Forecast> {
        return dtoList.map { map(it) }
    }

    private fun map(dto: ForecastItemDto): Forecast {
        val weatherEntry = dto.weather.firstOrNull()
        return Forecast(
            dateTime = dto.dateText,
            temperature = dto.main.temp,
            feelsLike = dto.main.feelsLike,
            tempMin = dto.main.tempMin,
            tempMax = dto.main.tempMax,
            pressure = dto.main.pressure,
            humidity = dto.main.humidity,
            weatherMain = weatherEntry?.main.orEmpty(),
            weatherDescription = weatherEntry?.description.orEmpty(),
            icon = weatherEntry?.icon.orEmpty()
        )
    }
}