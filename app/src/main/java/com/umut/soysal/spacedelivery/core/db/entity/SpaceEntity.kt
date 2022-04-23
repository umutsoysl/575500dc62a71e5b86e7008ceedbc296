package com.umut.soysal.spacedelivery.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umut.soysal.spacedelivery.core.constant.GlobalConstant

@Entity(tableName = GlobalConstant.DB_SPACE_TABLE)
data class SpaceEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "capacity")
    val capacity: Int = 0,

    @ColumnInfo(name = "speed")
    val speed: Int = 0,

    @ColumnInfo(name = "durability")
    val durability: Int = 0,
)