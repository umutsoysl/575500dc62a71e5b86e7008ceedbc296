package com.umut.soysal.spacedelivery.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umut.soysal.spacedelivery.core.constant.GlobalConstant
import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity

@Dao
interface SpaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpace(space: SpaceEntity)

    @Query("SELECT * FROM ${GlobalConstant.DB_SPACE_TABLE}")
    fun getSpace(): List<SpaceEntity>

    @Query("DELETE FROM ${GlobalConstant.DB_SPACE_TABLE}")
    fun deleteAllSpace()
}