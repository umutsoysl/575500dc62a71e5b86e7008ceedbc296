package com.umut.soysal.spacedelivery.data.station.domain

import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.data.station.model.StationModel
import com.umut.soysal.spacedelivery.data.station.repository.StationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StationUseCaseImpl @Inject constructor(
    private val stationRepository: StationRepository
) : StationUseCase {

    override suspend fun fetchStationList(searchKey: String): Flow<List<StationEntity>> {
        return stationRepository.fetchStationList(searchKey)
    }

    override suspend fun updateFavoriteStation(stationId: Int, favorite: Boolean) {
        stationRepository.updateFavoriteStation(stationId, favorite)
    }

    override suspend fun getFavoriteStationList(): Flow<List<StationEntity>> =
        stationRepository.getFavoriteStationList()

}