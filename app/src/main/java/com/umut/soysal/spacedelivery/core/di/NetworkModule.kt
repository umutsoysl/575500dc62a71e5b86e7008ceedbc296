package com.umut.soysal.spacedelivery.core.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.umut.soysal.spacedelivery.core.SpaceApplication
import com.umut.soysal.spacedelivery.core.constant.GlobalConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val URL = "https://run.mocky.io/v3/e7211664-cbb6-4357-9c9d-f12bf8bab2e2/"

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): SpaceApplication {
        return app as SpaceApplication
    }

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient, moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(moshiConverterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(GlobalConstant.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(GlobalConstant.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(GlobalConstant.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }
}