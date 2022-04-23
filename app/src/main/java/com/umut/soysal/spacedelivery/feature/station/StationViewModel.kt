package com.umut.soysal.spacedelivery.feature.station

import com.umut.soysal.spacedelivery.core.base.BaseViewModel
import com.umut.soysal.spacedelivery.data.station.domain.StationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StationViewModel @Inject constructor(
    private val stationUseCase: StationUseCase
): BaseViewModel() {



    fun fetchStationList() {

    }
}