package com.umut.soysal.spacedelivery.feature.space

import androidx.lifecycle.viewModelScope
import com.umut.soysal.spacedelivery.core.base.BaseViewModel
import com.umut.soysal.spacedelivery.core.db.entity.SpaceEntity
import com.umut.soysal.spacedelivery.data.space.domain.SpaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CreateSpaceViewModel @Inject constructor(
    private val spaceUseCase: SpaceUseCase
): BaseViewModel() {

    fun insertSpace(name: String, speed: Int, capacity: Int, durability: Int) = runBlocking {
        viewModelScope.launch {
            val space = SpaceEntity(
                name = name,
                speed = speed,
                capacity = capacity,
                durability = durability
            )
            spaceUseCase.insertSpace(space)
        }
    }
}