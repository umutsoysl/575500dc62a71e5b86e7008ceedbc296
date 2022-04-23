package com.umut.soysal.spacedelivery.data.station.domain

import com.umut.soysal.spacedelivery.data.station.model.StationModel
import kotlinx.coroutines.flow.Flow

interface StationUseCase {
    suspend fun fetchStationList() : Flow<List<StationModel>>

    suspend fun searchStation(searchKey: String) : Flow<List<StationModel>>
}