package com.bogdanov.weatherlookupapp.di

import com.bogdanov.weatherlookupapp.domain.repository.ForecastRepository
import com.bogdanov.weatherlookupapp.data.repostiory.ForecastRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindForecastRepository(
        impl: ForecastRepositoryImpl
    ): ForecastRepository
}