package com.umut.soysal.spacedelivery.core.db.di

import android.content.Context
import androidx.room.Room
import com.umut.soysal.spacedelivery.core.constant.GlobalConstant
import com.umut.soysal.spacedelivery.core.db.SpaceXDatabase
import com.umut.soysal.spacedelivery.core.db.dao.PlanetDao
import com.umut.soysal.spacedelivery.core.db.dao.SpaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): SpaceXDatabase {
        return Room.databaseBuilder(
            context,
            SpaceXDatabase::class.java,
            GlobalConstant.LOCALE_DB
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideExecutorService(): ExecutorService {
        return Executors.newFixedThreadPool(4)
    }

    @Singleton
    @Provides
    fun providePlanetDao(database: SpaceXDatabase): PlanetDao {
        return database.planetDao()
    }

    @Singleton
    @Provides
    fun provideSpaceDao(database: SpaceXDatabase): SpaceDao {
        return database.spaceDao()
    }
}