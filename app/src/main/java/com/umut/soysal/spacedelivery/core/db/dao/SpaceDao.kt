package com.umut.soysal.spacedelivery.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity

@Dao
interface SpaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpace(space: SpaceEntity)
}