package com.umut.soysal.spacedelivery.data.station.mapper

import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.data.station.model.StationModel
import javax.inject.Inject

class StationMapper @Inject constructor() {

    fun mapToEntity(
        response: StationModel
    ): StationEntity =
        with(response) {
            StationEntity(
                name = response.name,
                coordinateX = response.coordinateX,
                coordinateY = response.coordinateY,
                capacity = response.capacity,
                stock = response.stock,
                need = response.need,
            )
        }

    fun mapToResponse(
        entity: StationEntity
    ): StationModel =
        with(entity) {
            StationModel(
                id = entity.id,
                name = entity.name,
                coordinateX = entity.coordinateX,
                coordinateY = entity.coordinateY,
                capacity = entity.capacity,
                stock = entity.stock,
                need = entity.need,
                isFavorite = entity.isFavorite,
            )
        }
}