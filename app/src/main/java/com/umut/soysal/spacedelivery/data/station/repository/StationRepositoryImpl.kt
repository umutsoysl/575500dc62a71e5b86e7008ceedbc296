package com.umut.soysal.spacedelivery.data.station.repository

import androidx.annotation.VisibleForTesting
import com.umut.soysal.spacedelivery.core.di.IoDispatcher
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

    override suspend fun fetchStationList(searchKey: String): Flow<List<StationModel>> {
        return flow {
            fetchStationListDataFromLocal().collect { localeResponse ->
                if (localeResponse.isNullOrEmpty()) {
                    fetchStationListDataFromRemote().collect { remoteResponse ->
                        emit(remoteResponse)
                    }
                }
            }
        }.flowOn(ioDispatcher)
    }


    @VisibleForTesting
    suspend fun fetchStationListDataFromLocal() =
        stationLocalDataSource.favoriteStationList()


    @VisibleForTesting
    suspend fun fetchStationListDataFromRemote(): Flow<List<StationModel>> {
        return flow {
            stationRemoteDataSource.fetchStationList()
                .collect { response ->
                    response.onEach { station ->
                        insertStationResponse(station)
                    }
                    emit(response)
                }
        }.flowOn(ioDispatcher)
    }


    @VisibleForTesting
    suspend fun insertStationResponse(remoteData: StationModel) {
        stationLocalDataSource.insertStation(
            mapper.mapToEntity(
                response = remoteData
            )
        )
    }
}
