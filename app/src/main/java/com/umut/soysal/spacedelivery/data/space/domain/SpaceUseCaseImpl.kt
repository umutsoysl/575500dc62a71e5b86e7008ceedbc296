package com.umut.soysal.spacedelivery.data.space.domain

import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity
import com.umut.soysal.spacedelivery.data.space.repository.SpaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpaceUseCaseImpl @Inject constructor(
    private val spaceRepository: SpaceRepository
): SpaceUseCase {

    override suspend fun getSpace(): Flow<SpaceEntity> =
        spaceRepository.getSpace()

    override suspend fun insertSpace(space: SpaceEntity) =
        spaceRepository.insertSpace(space)

    override suspend fun deleteAllSpace() =
        spaceRepository.deleteAllSpace()
}