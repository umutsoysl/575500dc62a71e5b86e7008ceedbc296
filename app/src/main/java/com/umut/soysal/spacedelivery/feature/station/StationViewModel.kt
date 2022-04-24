package com.umut.soysal.spacedelivery.feature.station

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.umut.soysal.spacedelivery.core.base.BaseViewModel
import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.core.util.DistanceExtension
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

    private val _distanceState = MutableStateFlow<Double>(0.0)
    val distanceState: StateFlow<Double>
        get() = _distanceState

    private val _sumDistanceState = MutableStateFlow<Double>(0.0)
    val sumDistanceState: StateFlow<Double>
        get() = _sumDistanceState

    private val _damageCapacityState = MutableStateFlow<Int>(100)
    val damageCapacityState: StateFlow<Int>
        get() = _damageCapacityState

    lateinit var timer : CountDownTimer

    private val _currentDamageTime = MutableStateFlow<Int>(100)
    val currentDamageTime: StateFlow<Int>
        get() = _currentDamageTime

    private val _startTravel = MutableStateFlow<Boolean>(false)
    val startTravel: StateFlow<Boolean>
        get() = _startTravel

    private val _worldStationState = MutableStateFlow<StationEntity>(StationEntity())

    fun getSpace() {
        viewModelScope.launch {
            spaceUseCase.getSpace().collect { space->
                _spaceDataState.value = space
                _ugsState.value = space.capacity*10000
                _eusState.value = space.speed*20
                _dsState.value = space.durability*10000
                _damageCapacityState.value = 100
                _currentDamageTime.value = _dsState.value.div(1000).minus(1)
            }
        }
    }


    fun fetchStationList(searchKey: String = "") {
        viewModelScope.launch {
            _stationListState.value = listOf()
            stationUseCase.fetchStationList("%${searchKey.toUpperCase()}%").collect { list ->
                list.forEach { item ->
                    if(item.name != "Dünya") {
                        _stationListState.value += item
                    } else {
                        _currentStation.value = item
                        _worldStationState.value = item
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

    fun updateCurrentStation(station: StationEntity) {
        _currentStation.value = station
    }

    fun setWorldStation() {
        _currentStation.value = _worldStationState.value
        _startTravel.value = true
        timer.cancel()
    }

    fun ugsValueUpdate(need: Int) {
        _ugsState.value = _ugsState.value.minus(need)
    }

    fun calculateDistance(station: StationEntity, startTravel: Boolean = false) {
        _distanceState.value = DistanceExtension.calculateDistance(
            currentStation.value.coordinateX,
            station.coordinateX,
            currentStation.value.coordinateY,
            station.coordinateY,
        )
        if(startTravel) {
            _sumDistanceState.value = _sumDistanceState.value + _distanceState.value
            //10 birim mesafe aldi
            if(_sumDistanceState.value>10) {
                _eusState.value = _eusState.value.minus(10)
            }
            updateCurrentStation(station)
        }
    }

    fun travel() {
        _startTravel.value = true
        timer = object: CountDownTimer((_currentDamageTime.value*1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _currentDamageTime.value = _currentDamageTime.value?.minus(1)
            }
            override fun onFinish() {
                _damageCapacityState.value = _damageCapacityState.value -10
                _currentStation.value = _worldStationState.value
                _startTravel.value = false
            }
        }
        timer.start()
    }
}