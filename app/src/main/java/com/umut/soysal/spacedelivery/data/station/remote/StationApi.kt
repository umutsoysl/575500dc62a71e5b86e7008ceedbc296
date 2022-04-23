package com.umut.soysal.spacedelivery.data.station.remote

import com.umut.soysal.spacedelivery.data.station.model.StationModel
import retrofit2.Response
import retrofit2.http.GET

interface StationApi {
    @GET(".")
    suspend fun fetchStationList(): Response<List<StationModel>>
}