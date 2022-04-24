package com.umut.soysal.spacedelivery.data.station.repository

import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.core.di.IoDispatcher
import com.umut.soysal.spacedelivery.core.state.ServiceResult
import com.umut.soysal.spacedelivery.data.station.mapper.StationMapper
import com.umut.soysal.spacedelivery.data.station.model.StationModel
import com.umut.soysal.spacedelivery.data.station.remote.StationRemoteDataSource
import com.umut.soysal.spacedelivery.data.station.local.StationLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StationRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val stationRemoteDataSource: StationRemoteDataSource,
    private val stationLocalDataSource: StationLocalDataSource,
    private val mapper: StationMapper
): StationRepository {

    override suspend fun fetchStationList(searchKey: String): Flow<List<StationEntity>> {
        return flow {
            fetchStationListDataFromLocal().collect { localeResponse ->
                if (localeResponse.isNullOrEmpty()) {
                    fetchStationListDataFromRemote().collect { remoteResponse ->
                        emit(remoteResponse)
                    }
                } else {
                    searchStationListDataFromLocal(searchKey).collect { response ->
                        emit(response)
                    }
                }
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun updateFavoriteStation(stationId: Int, favorite: Boolean) {
        stationLocalDataSource.updateFavoriteStation(stationId, favorite)
    }

    override suspend fun getFavoriteStationList(): Flow<List<StationEntity>> =
        stationLocalDataSource.favoriteStationList()


    suspend fun searchStationListDataFromLocal(searchKey: String): Flow<List<StationEntity>> =
        stationLocalDataSource.searchStation(searchKey)

    private suspend fun fetchStationListDataFromLocal() =
        stationLocalDataSource.searchStation("%%")



    suspend fun fetchStationListDataFromRemote(): Flow<List<StationEntity>> {
        return flow {
            stationRemoteDataSource.fetchStationList()
                .collect { response ->
                    when(response) {
                        is ServiceResult.Success -> {
                            var entityStationList: List<StationEntity> = listOf()
                            (response as ServiceResult.Success).data.onEach { station ->
                                insertStationResponse(station)
                                entityStationList +=  mapper.mapToEntity(station)
                            }
                            emit(entityStationList)
                        }
                    }
                }
        }.flowOn(ioDispatcher)
    }


    suspend fun insertStationResponse(remoteData: StationModel)  {
        stationLocalDataSource.insertStation(
            mapper.mapToEntity(
                response = remoteData
            )
        )
    }
}
