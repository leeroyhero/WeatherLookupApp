package com.bogdanov.weatherlookupapp.domain.mapper

import com.bogdanov.weatherlookupapp.data.network.dto.ForecastItemDto
import com.bogdanov.weatherlookupapp.data.network.dto.MainInfoDto
import com.bogdanov.weatherlookupapp.data.network.dto.WeatherInfoDto
import com.bogdanov.weatherlookupapp.domain.model.Forecast
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ForecastMapperTest {

    private lateinit var mapper: ForecastMapper

    @Before
    fun setup() {
        mapper = ForecastMapper()
    }

    @Test
    fun `mapList maps single ForecastItemDto with weather`() {
        val dto = ForecastItemDto(
            timestamp = 1650000000,
            dateText = "2025-07-07 15:00:00",
            main = MainInfoDto(25.0, 24.5, 20.0, 28.0, 1013, 60),
            weather = listOf(WeatherInfoDto("Clouds", "scattered clouds", "03d"))
        )

        val expected = Forecast(
            dateTime = "2025-07-07 15:00:00",
            temperature = 25.0,
            feelsLike = 24.5,
            tempMin = 20.0,
            tempMax = 28.0,
            pressure = 1013,
            humidity = 60,
            weatherMain = "Clouds",
            weatherDescription = "scattered clouds",
            icon = "03d"
        )

        val result = mapper.mapList(listOf(dto))

        assertEquals(listOf(expected), result)
    }

    @Test
    fun `mapList maps ForecastItemDto with empty weather list`() {
        val dto = ForecastItemDto(
            timestamp = 1650000000,
            dateText = "2025-07-07 15:00:00",
            main = MainInfoDto(25.0, 24.5, 20.0, 28.0, 1013, 60),
            weather = emptyList()
        )

        val expected = Forecast(
            dateTime = "2025-07-07 15:00:00",
            temperature = 25.0,
            feelsLike = 24.5,
            tempMin = 20.0,
            tempMax = 28.0,
            pressure = 1013,
            humidity = 60,
            weatherMain = "",
            weatherDescription = "",
            icon = ""
        )

        val result = mapper.mapList(listOf(dto))

        assertEquals(listOf(expected), result)
    }
}