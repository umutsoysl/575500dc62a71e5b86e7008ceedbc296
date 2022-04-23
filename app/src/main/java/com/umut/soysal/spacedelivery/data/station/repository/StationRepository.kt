package com.umut.soysal.spacedelivery.data.station.repository

import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    suspend fun fetchStationList(searchKey: String = ""): Flow<List<StationEntity>>
}