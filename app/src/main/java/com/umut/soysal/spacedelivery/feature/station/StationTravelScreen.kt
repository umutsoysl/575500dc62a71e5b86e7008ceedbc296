package com.umut.soysal.spacedelivery.feature.station

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun StationTravelScreen(
    navigationController: NavController? = null,
    stationViewModel: StationViewModel = hiltViewModel()
) {

    val spaceData by stationViewModel.spaceDataState.collectAsState()

    

    LaunchedEffect(Unit) {
        stationViewModel.getSpace()
        stationViewModel.fetchStationList()
    }
}