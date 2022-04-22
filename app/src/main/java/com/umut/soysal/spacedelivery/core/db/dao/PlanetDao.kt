package com.umut.soysal.spacedelivery.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umut.soysal.spacedelivery.core.constant.GlobalConstant
import com.umut.soysal.spacedelivery.core.db.entity.PlanetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanet(planet: PlanetEntity)

    @Query("SELECT * FROM ${GlobalConstant.DB_PLANET_TABLE} WHERE name LIKE :searchKey")
    fun searchPlanet(searchKey: String?): Flow<List<PlanetEntity>>
}