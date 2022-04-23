package com.umut.soysal.spacedelivery.data.station.domain

import com.umut.soysal.spacedelivery.data.station.model.StationModel
import com.umut.soysal.spacedelivery.data.station.repository.StationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StationUseCaseImpl @Inject constructor(
    private val stationRepository: StationRepository
) : StationUseCase {

    override suspend fun fetchStationList(): Flow<List<StationModel>> =
        stationRepository.fetchStationList("")

    override suspend fun searchStation(searchKey: String): Flow<List<StationModel>> =
        stationRepository.fetchStationList(searchKey)
}