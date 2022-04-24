package com.umut.soysal.spacedelivery.feature.splash

import androidx.lifecycle.viewModelScope
import com.umut.soysal.spacedelivery.core.base.BaseViewModel
import com.umut.soysal.spacedelivery.data.space.domain.SpaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarterViewModel @Inject constructor(
    private val spaceUseCase: SpaceUseCase
): BaseViewModel() {
    init {
        viewModelScope.launch {
            spaceUseCase.deleteAllSpace()
        }
    }
}