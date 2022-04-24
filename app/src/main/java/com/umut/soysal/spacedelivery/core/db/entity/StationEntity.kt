package com.umut.soysal.spacedelivery.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umut.soysal.spacedelivery.core.constant.GlobalConstant

@Entity(tableName = GlobalConstant.DB_PLANET_TABLE)
data class StationEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "coordinateX")
    val coordinateX: Double = 0.0,

    @ColumnInfo(name = "coordinateY")
    val coordinateY: Double = 0.0,

    @ColumnInfo(name = "capacity")
    val capacity: Int = 0,

    @ColumnInfo(name = "stock")
    val stock: Int = 0,

    @ColumnInfo(name = "need")
    val need: Int = 0,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)
