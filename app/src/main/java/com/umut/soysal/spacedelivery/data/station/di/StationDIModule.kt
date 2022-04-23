package com.umut.soysal.spacedelivery.data.station.di

import com.umut.soysal.spacedelivery.data.station.domain.StationUseCase
import com.umut.soysal.spacedelivery.data.station.domain.StationUseCaseImpl
import com.umut.soysal.spacedelivery.data.station.remote.StationApi
import com.umut.soysal.spacedelivery.data.station.repository.StationRepository
import com.umut.soysal.spacedelivery.data.station.repository.StationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class StationDIModule {

    @Binds
    abstract fun provideStationRepository(
        stationRepositoryImpl: StationRepositoryImpl
    ): StationRepository

    @Binds
    abstract fun provideStationUseCase(
        stationUseCaseImpl: StationUseCaseImpl
    ): StationUseCase

    companion object {
        @Provides
        fun provideStationApi(
            retrofit: Retrofit
        ): StationApi = retrofit.create(StationApi::class.java)

    }
}