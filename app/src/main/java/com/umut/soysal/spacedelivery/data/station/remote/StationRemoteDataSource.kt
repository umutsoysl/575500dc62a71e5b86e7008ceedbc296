package com.umut.soysal.spacedelivery.data.station.remote

import com.umut.soysal.spacedelivery.data.station.model.StationModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StationRemoteDataSource @Inject constructor(
    private val stationApi: StationApi
){
    suspend fun fetchStationList(): Flow<List<StationModel>> =
        stationApi.fetchStationList()
}