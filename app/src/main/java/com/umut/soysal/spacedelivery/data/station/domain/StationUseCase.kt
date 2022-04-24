package com.umut.soysal.spacedelivery.data.station.domain

import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import kotlinx.coroutines.flow.Flow

interface StationUseCase {
    suspend fun fetchStationList(searchKey: String) : Flow<List<StationEntity>>

    suspend fun updateFavoriteStation(stationId: Int, favorite: Boolean)

    suspend fun getFavoriteStationList() : Flow<List<StationEntity>>
}