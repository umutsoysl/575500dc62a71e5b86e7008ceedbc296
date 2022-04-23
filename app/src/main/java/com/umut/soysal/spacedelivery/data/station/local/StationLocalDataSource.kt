package com.umut.soysal.spacedelivery.data.station.local

import com.umut.soysal.spacedelivery.core.db.dao.StationDao
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.core.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class StationLocalDataSource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val stationDao: StationDao,
    private val databaseWriteExecutor: ExecutorService
){
    suspend fun insertStation(station: StationEntity) = withContext(ioDispatcher) {
        databaseWriteExecutor.execute {
            stationDao.insertStation(station)
        }
    }


    suspend fun searchStation(searchKey: String): Flow<List<StationEntity>> {
        return flow {
            emit(stationDao.searchStation(searchKey))
        }
    }

    suspend fun favoriteStationList(): Flow<List<StationEntity>> {
        return flow {
            emit(stationDao.favoriteStationList())
        }
    }

}