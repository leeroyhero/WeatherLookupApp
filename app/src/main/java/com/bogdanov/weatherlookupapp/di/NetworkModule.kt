package com.bogdanov.weatherlookupapp.di

import com.bogdanov.weatherlookupapp.data.network.service.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @OpenWeatherApi
    fun provideOpenWeatherBaseUrl(): String = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    @OpenWeatherApi
    fun provideOpenWeatherRetrofit(@OpenWeatherApi baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(@OpenWeatherApi retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}