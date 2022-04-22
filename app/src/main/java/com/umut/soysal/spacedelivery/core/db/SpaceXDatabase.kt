package com.umut.soysal.spacedelivery.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umut.soysal.spacedelivery.core.db.dao.PlanetDao
import com.umut.soysal.spacedelivery.core.db.dao.SpaceDao
import com.umut.soysal.spacedelivery.core.db.entity.PlanetEntity

@Database(
    entities = [
        PlanetEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class SpaceXDatabase : RoomDatabase() {
    abstract fun planetDao(): PlanetDao
    abstract fun spaceDao(): SpaceDao
}