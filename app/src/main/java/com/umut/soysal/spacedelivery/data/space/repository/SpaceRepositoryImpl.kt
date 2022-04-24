package com.umut.soysal.spacedelivery.data.space.repository

import com.umut.soysal.spacedelivery.core.db.dao.SpaceDao
import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity
import com.umut.soysal.spacedelivery.core.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class SpaceRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val spaceDao: SpaceDao,
    private val databaseExecutor: ExecutorService
) : SpaceRepository {

    override suspend fun getSpace(): Flow<SpaceEntity> {
        return flow {
            emit(spaceDao.getSpace().first())
        }.flowOn(ioDispatcher)
    }

    override suspend fun insertSpace(space: SpaceEntity) {
        databaseExecutor.execute {
            spaceDao.insertSpace(space)
        }
    }

    override suspend fun deleteAllSpace() {
        databaseExecutor.execute {
            spaceDao.deleteAllSpace()
        }
    }

}