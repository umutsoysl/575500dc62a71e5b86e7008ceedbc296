package com.umut.soysal.spacedelivery.feature.favorite

import androidx.lifecycle.viewModelScope
import com.umut.soysal.spacedelivery.core.base.BaseViewModel
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.data.station.domain.StationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class FavoriteStationViewModel @Inject constructor(
    private val stationUseCase: StationUseCase
): BaseViewModel() {

    private val _stationListState = MutableStateFlow<List<StationEntity>>(listOf())
    val stationListState: StateFlow<List<StationEntity>>
        get() = _stationListState

    fun getFavoriteStationList() {
        viewModelScope.launch {
            stationUseCase.getFavoriteStationList().collect {
                _stationListState.value = it
            }
        }
    }

    fun updateFavoriteStation(station: StationEntity) = runBlocking {
        viewModelScope.launch {
            stationUseCase.updateFavoriteStation(station.id, false)
        }
    }
}