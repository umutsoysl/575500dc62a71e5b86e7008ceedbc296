package com.umut.soysal.spacedelivery.feature.station

import androidx.lifecycle.viewModelScope
import com.umut.soysal.spacedelivery.core.base.BaseViewModel
import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.data.space.domain.SpaceUseCase
import com.umut.soysal.spacedelivery.data.station.domain.StationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationViewModel @Inject constructor(
    private val stationUseCase: StationUseCase,
    private val spaceUseCase: SpaceUseCase
): BaseViewModel() {

    private val _spaceDataState = MutableStateFlow<SpaceEntity>(SpaceEntity())
    val spaceDataState: StateFlow<SpaceEntity>
        get() = _spaceDataState

    private val _stationListState = MutableStateFlow<List<StationEntity>>(listOf())
    val stationListState: StateFlow<List<StationEntity>>
        get() = _stationListState

    fun getSpace() {
        viewModelScope.launch {
            spaceUseCase.getSpace().collect {
                _spaceDataState.value = it
            }
        }
    }


    fun fetchStationList(searchKey: String = "%%") {
        viewModelScope.launch {
            stationUseCase.fetchStationList(searchKey).collect {
                _stationListState.value = it
            }
        }
    }
}