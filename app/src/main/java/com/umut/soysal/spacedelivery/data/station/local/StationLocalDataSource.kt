package com.umut.soysal.spacedelivery.data.station.local

import com.umut.soysal.spacedelivery.core.db.dao.StationDao
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StationLocalDataSource @Inject constructor(
    private val stationDao: StationDao
){
    suspend fun insertStation(station: StationEntity) =
        stationDao.insertStation(station)

    suspend fun searchStation(searchKey: String): Flow<List<StationEntity>> =
        stationDao.searchStation(searchKey)

    suspend fun favoriteStationList(): Flow<List<StationEntity>> =
        stationDao.favoriteStationList()
}