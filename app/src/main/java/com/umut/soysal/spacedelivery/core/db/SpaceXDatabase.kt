package com.umut.soysal.spacedelivery.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umut.soysal.spacedelivery.core.db.dao.StationDao
import com.umut.soysal.spacedelivery.core.db.dao.SpaceDao
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity

@Database(
    entities = [
        StationEntity::class,
        SpaceEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class SpaceXDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao
    abstract fun spaceDao(): SpaceDao
}