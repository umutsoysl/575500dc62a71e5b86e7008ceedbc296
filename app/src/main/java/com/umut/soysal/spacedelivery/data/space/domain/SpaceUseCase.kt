package com.umut.soysal.spacedelivery.data.space.domain

import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity
import kotlinx.coroutines.flow.Flow

interface SpaceUseCase {
    suspend fun getSpace(): Flow<SpaceEntity>

    suspend fun insertSpace(space: SpaceEntity)

    suspend fun deleteAllSpace()
}