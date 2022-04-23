package com.umut.soysal.spacedelivery.data.station.model

import com.squareup.moshi.Json

data class StationModel(
    val id: Int = 0,

    @Json(name = "name")
    val name: String?,

    @Json(name = "coordinateX")
    val coordinateX: Double = 0.0,

    @Json(name = "coordinateY")
    val coordinateY: Double = 0.0,

    @Json(name = "capacity")
    val capacity: Int = 0,

    @Json(name = "stock")
    val stock: Int = 0,

    @Json(name = "need")
    val need: Int = 0,

    val isFavorite: Boolean = false
)