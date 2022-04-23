package com.umut.soysal.spacedelivery.data.station.remote

import com.umut.soysal.spacedelivery.data.station.model.StationModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface StationApi {
    @GET
    suspend fun fetchStationList(): Flow<List<StationModel>>
}