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

    private val _currentStation = MutableStateFlow<StationEntity>(StationEntity())
    val currentStation: StateFlow<StationEntity>
        get() = _currentStation

    private val _stationListState = MutableStateFlow<List<StationEntity>>(listOf())
    val stationListState: StateFlow<List<StationEntity>>
        get() = _stationListState

    private val _ugsState = MutableStateFlow<Int>(0)
    val ugsState: StateFlow<Int>
        get() = _ugsState

    private val _eusState = MutableStateFlow<Int>(0)
    val eusState: StateFlow<Int>
        get() = _eusState

    private val _dsState = MutableStateFlow<Int>(0)
    val dsState: StateFlow<Int>
        get() = _dsState

    private val _damageCapacityState = MutableStateFlow<Int>(0)
    val damageCapacityState: StateFlow<Int>
        get() = _damageCapacityState

    fun getSpace() {
        viewModelScope.launch {
            spaceUseCase.getSpace().collect { space->
                _spaceDataState.value = space
                _ugsState.value = space.capacity*10000
                _eusState.value = space.speed*20
                _dsState.value = space.durability*10000
                _damageCapacityState.value = 100
            }
        }
    }


    fun fetchStationList(searchKey: String = "%%") {
        viewModelScope.launch {
            stationUseCase.fetchStationList(searchKey).collect { list ->
                list.forEach { item ->
                    if(item.name != "Dünya") {
                        _stationListState.value += item
                    } else {
                        _currentStation.value = item
                    }
                }
            }
        }
    }

    fun updateFavoriteStation(stationId: Int, favorite: Boolean) {
        viewModelScope.launch {
            stationUseCase.updateFavoriteStation(stationId, favorite)
        }
    }
}