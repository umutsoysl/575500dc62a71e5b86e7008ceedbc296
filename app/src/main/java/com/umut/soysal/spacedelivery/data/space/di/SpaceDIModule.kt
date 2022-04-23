package com.umut.soysal.spacedelivery.data.space.di

import com.umut.soysal.spacedelivery.data.space.domain.SpaceUseCase
import com.umut.soysal.spacedelivery.data.space.domain.SpaceUseCaseImpl
import com.umut.soysal.spacedelivery.data.space.repository.SpaceRepository
import com.umut.soysal.spacedelivery.data.space.repository.SpaceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SpaceDIModule {

    @Binds
    abstract fun provideSpaceRepository(
        spaceRepositoryImpl: SpaceRepositoryImpl
    ): SpaceRepository

    @Binds
    abstract fun provideSpaceUseCase(
        spaceUseCaseImpl: SpaceUseCaseImpl
    ): SpaceUseCase
}